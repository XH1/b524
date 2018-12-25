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

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

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

}
