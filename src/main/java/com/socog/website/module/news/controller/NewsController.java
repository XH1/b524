package com.socog.website.module.news.controller;

import com.socog.website.module.news.entity.News;
import com.socog.website.module.news.entity.NewsType;
import com.socog.website.module.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xinghao
 * @descreption
 * @date 2018/12/20
 */

@Controller
@RequestMapping("/news")
public class NewsController {

    private final
    NewsRepository newsRepository;

    @Autowired
    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("")
    public String getAll(@RequestParam(required = false , defaultValue = "0") int page, Model model) {
        Pageable newsPageable = PageRequest.of(page, 3,Sort.Direction.DESC,"id");
        Page<News> newsPage = newsRepository.findByNewsType(NewsType.NEWS,newsPageable);

        model.addAttribute("newsPage", newsPage);

        Page<News> notPage = newsRepository.findByNewsType(NewsType.NOTIFICATION,newsPageable);
        model.addAttribute("notPage",notPage);

        return "eventlist";
    }

    @ResponseBody
    @GetMapping("/newspage")
    public Page<News> getNewsPage(@RequestParam(required = false , defaultValue = "0") int newsPageNow) {
        Pageable newsPageable = PageRequest.of(newsPageNow, 3,Sort.Direction.DESC,"id");
        Page<News> newsPage = newsRepository.findByNewsType(NewsType.NEWS,newsPageable);
        return newsPage;
    }

}
