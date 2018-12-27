package com.socog.website.module.paper.controller;

import com.socog.website.module.paper.entity.Paper;
import com.socog.website.module.paper.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/paper")
public class PaperController {
    private final PaperRepository paperRepository;
    @Autowired
    public PaperController(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    //paperlist“≥√Ê
    @GetMapping("/paper")
    public String getAll(@RequestParam(defaultValue = "all") String searchWay, @RequestParam(defaultValue = "0") String specificWay,
                         @RequestParam(defaultValue = "0") int page, Model model) {
        System.out.println("searchWay£∫"+searchWay+",specificWay£∫"+specificWay);
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        Page<Paper> paperPage =checkPage(searchWay,specificWay,pageable);
        List<Map<String,Object>> year_count=paperRepository.groupByYear();
        List<Map<String,Object>> type_count=paperRepository.groupByType();
        List<Map<String,Object>> area_count=paperRepository.groupByArea();
        List<Map<String,Object>> firstThreeYearCount=new ArrayList<>();
        List<Map<String,Object>> restYearCount=new ArrayList<>();
        if (year_count.size()>3){
            for (int i=0;i<3;i++){
                firstThreeYearCount.add(year_count.get(i));
            }
            for(int j=3;j<year_count.size();j++){
                restYearCount.add(year_count.get(j));
            }
        }else{
            firstThreeYearCount=year_count;
        }
        model.addAttribute("paperPage", paperPage);
        model.addAttribute("searchWay", searchWay);
        model.addAttribute("specificWay", specificWay);
        //model.addAttribute("year_count", year_count);
        model.addAttribute("type_count", type_count);
        model.addAttribute("area_count", area_count);
        //model.addAttribute("testYearCount", year_count.size());
        model.addAttribute("firstThreeYearCount", firstThreeYearCount);
        model.addAttribute("restYearCount", restYearCount);

        return "research_paper";
    }
    @ResponseBody
    @GetMapping("/paperpage")
    public Page<Paper> getPaperPage(@RequestParam(defaultValue = "all") String searchWay, @RequestParam(defaultValue = "0") String specificWay,
                                      @RequestParam(defaultValue = "0") int paperPageNow) {

        Page<Paper> paperPage = null;
        Pageable pageable = PageRequest.of(paperPageNow, 3, Sort.Direction.DESC, "id");
        paperPage = checkPage(searchWay, specificWay, pageable);
        return paperPage;
    }
    private Page<Paper> checkPage(String searchWay, String specificWay, Pageable pageable) {
        Page<Paper> paperPage = null;
        if ("all".equals(searchWay) || "".equals(searchWay)) {
            paperPage = paperRepository.findAll(pageable);
        } else if ("year".equals(searchWay)) {
            paperPage = paperRepository.findByYear( specificWay, pageable);
        } else if ("type".equals(searchWay)) {
            paperPage = paperRepository.findByType(specificWay, pageable);
        } else if ("area".equals(searchWay)) {
            paperPage = paperRepository.findByArea(specificWay, pageable);
        }
        return paperPage;
    }


    //*******************************************************************************
    //paper_area“≥√Ê
     @GetMapping("/paperArea")
    public String getAll1(@RequestParam(defaultValue = "0") String area, @RequestParam(defaultValue = "all") String year,
                         @RequestParam(defaultValue = "0") int page, Model model) {
         System.out.println("area:"+area+",year:"+year+",page:"+page);
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        Page<Paper> paperPage =checkPage1(area,year,pageable);
        List<Map<String,Object>> year_count=new ArrayList<>();
        year_count=paperRepository.groupByYear1(area);
         List<Map<String,Object>> firstThreeYearCount=new ArrayList<>();
         List<Map<String,Object>> restYearCount=new ArrayList<>();
         System.out.println("year_count.sizeŒ™£∫"+year_count.size());
         if (year_count.size()>3){
             for (int i=0;i<3;i++){
                 firstThreeYearCount.add(year_count.get(i));
             }
             for(int j=3;j<year_count.size();j++){
                 restYearCount.add(year_count.get(j));
             }
         }else{
             firstThreeYearCount=year_count;
         }
         System.out.println("restYearCount:"+restYearCount.size());
        model.addAttribute("paperPage", paperPage);
        model.addAttribute("area", area);
        model.addAttribute("year", year);
        //model.addAttribute("year_count", year_count);
         model.addAttribute("firstThreeYearCount", firstThreeYearCount);
         model.addAttribute("restYearCount", restYearCount);
        return "paper_area";
    }
    @ResponseBody
    @GetMapping("/paperAreaPage")
    public Page<Paper> getPaperPage1(@RequestParam(defaultValue = "0") String area, @RequestParam(defaultValue = "all") String year,
                                     @RequestParam(defaultValue = "0") int paperPageNow) {
        System.out.println("paperPageNow:"+paperPageNow);
        Page<Paper> paperPage = null;
        Pageable pageable = PageRequest.of(paperPageNow, 3, Sort.Direction.DESC, "id");
        paperPage = checkPage1(area, year, pageable);
        return paperPage;
    }
    private Page<Paper> checkPage1(String area, String year, Pageable pageable) {
        Page<Paper> paperPage = null;
        if ("all".equals(year) || "".equals(year)){
            paperPage=paperRepository.findByArea(area,pageable);
        }else{
            paperPage=paperRepository.findByAreaAndYear(area,year,pageable);
        }
        return paperPage;
    }
}
