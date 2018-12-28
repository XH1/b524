package com.socog.website.module.resource.controller;

import com.socog.website.module.resource.entity.Resource;
import com.socog.website.module.resource.entity.ResourceType;
import com.socog.website.module.resource.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xinghao
 * @descreption
 * @date 2018/12/26
 */

@Controller
@RequestMapping("/resource")
public class ResourceController {

    private final
    ResourceRepository resourceRepository;

    @Autowired
    public ResourceController(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @RequestMapping("resourcelist")
    public String getAll(@RequestParam(defaultValue = "SHARE")ResourceType type, Model model) {
        Pageable pageable = PageRequest.of(0, 7,Sort.Direction.DESC,"id");
        Page<Resource> page = resourceRepository.findByResourceType(type,pageable);
        model.addAttribute("type", type);
        model.addAttribute("page", page);
        return "resource";
    }

    @ResponseBody
    @RequestMapping("/respage")
    public Page<Resource> getResPage(@RequestParam(required = false , defaultValue = "0") int resPageNow,@RequestParam(defaultValue = "SHARE")ResourceType type, Model model) {
        Pageable pageable = PageRequest.of(resPageNow, 7,Sort.Direction.DESC,"id");
        return resourceRepository.findByResourceType(type,pageable);
    }

}
