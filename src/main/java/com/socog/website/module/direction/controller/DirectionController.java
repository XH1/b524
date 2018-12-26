package com.socog.website.module.direction.controller;

import com.socog.website.module.direction.entity.Direction;
import com.socog.website.module.direction.repository.DirectionRepository;
import com.socog.website.module.paper.entity.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qixiaohui
 * @descreption
 * @date 2018/12/23
 */

@Controller
@RequestMapping("/direction")
public class DirectionController  {
    private final
    DirectionRepository directionRepository;

    @Autowired
    public DirectionController(DirectionRepository directionRepository){this.directionRepository=directionRepository;}

    @GetMapping("/research_direction")
    public String getAll(Model model){
        List<Direction> directionList = new ArrayList<>();
        directionList = directionRepository.findAll();
        model.addAttribute("directionList",directionList);
        return "research_direction";
    }

   /* @GetMapping("/paper_area")
    public String getAll(@RequestParam(defaultValue = "0") String area, @RequestParam(defaultValue = "all") String year,
                         @RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        Page<Paper> paperPage =checkPage(area,year,pageable);
        List<Map<String,Object>> year_count=new ArrayList<>();
        year_count=directionRepository.groupByYear(area);
        model.addAttribute("paperPage", paperPage);
        model.addAttribute("area", area);
        model.addAttribute("year", year);
        model.addAttribute("year_count", year_count);
        return "paper_area";
    }*/

   /* @ResponseBody
    @GetMapping("/paperpage")
    public Page<Paper> getPersonPage(@RequestParam(defaultValue = "0") String area, @RequestParam(defaultValue = "all") String year,
                                     @RequestParam(defaultValue = "0") int paperPageNow) {

        Page<Paper> paperPage = null;
        Pageable pageable = PageRequest.of(paperPageNow, 3, Sort.Direction.DESC, "id");
        paperPage = checkPage(area, year, pageable);
        return paperPage;
    }
    private Page<Paper> checkPage(String area, String year, Pageable pageable) {
        Page<Paper> paperPage = null;
        if ("all".equals(year) || "".equals(year)){
            paperPage=directionRepository.findByArea(area,pageable);
        }else{
            paperPage=directionRepository.findByAreaAndYear(area,year,pageable);
        }
        return paperPage;
    }*/

}
