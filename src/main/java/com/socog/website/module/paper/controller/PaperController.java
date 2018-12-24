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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/paper")
public class PaperController {
    private final PaperRepository paperRepository;
    @Autowired
    public PaperController(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }
    @GetMapping("")
    public String getAll(@RequestParam(defaultValue = "all") String searchWay, @RequestParam(defaultValue = "0") String specificWay,
                         @RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        Page<Paper> paperPage =checkPage(searchWay,specificWay,pageable);
        List<Map<String,Object>> year_count=paperRepository.groupByYear();
        List<Map<String,Object>> type_count=paperRepository.groupByType();
        List<Map<String,Object>> area_count=paperRepository.groupByArea();
        model.addAttribute("paperPage", paperPage);
        model.addAttribute("searchWay", searchWay);
        model.addAttribute("specificWay", specificWay);
        model.addAttribute("year_count", year_count);
        model.addAttribute("type_count", type_count);
        model.addAttribute("area_count", area_count);
        System.out.println("查看paperPage");
        for (Paper paper:paperPage) {
            System.out.println(paper.getId());
        }
        System.out.println("查看year_count");
        for (Map<String,Object> map:year_count) {
            System.out.println(map.get("year"));
            System.out.println(map.get("counts"));
        }
        System.out.println("查看type_count");
        for (Map<String,Object> map:type_count) {
            System.out.println(map.get("type"));
            System.out.println(map.get("counts"));
        }
        System.out.println("查看area_count");
        for (Map<String,Object> map:area_count) {
            System.out.println(map.get("area"));
            System.out.println(map.get("counts"));
        }

        return "paperlist";
    }
    @ResponseBody
    @GetMapping("/paperpage")
    public Page<Paper> getPersonPage(@RequestParam(defaultValue = "all") String searchWay, @RequestParam(defaultValue = "0") String specificWay,
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
}
