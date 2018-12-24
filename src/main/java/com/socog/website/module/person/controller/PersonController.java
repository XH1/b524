package com.socog.website.module.person.controller;

import com.socog.website.module.person.entiy.Degree;
import com.socog.website.module.person.entiy.Person;
import com.socog.website.module.person.repository.PersonRepository;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinghao
 * @descreption
 * @date 2018/12/19
 */

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("")
    public String getAll(@RequestParam(defaultValue = "professor") String degree, @RequestParam(defaultValue = "false") boolean graduate,
                         @RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        Page<Person> personPage = checkPage(degree,graduate,pageable);
        model.addAttribute("personPage", personPage);
        model.addAttribute("degree", degree);
        model.addAttribute("graduate", graduate);
        return "personlist";
    }

    @ResponseBody
    @GetMapping("/personpage")
    public Page<Person> getPersonPage(@RequestParam(defaultValue = "professor") String degree, @RequestParam(defaultValue = "false") boolean graduate,
                                      @RequestParam(defaultValue = "0") int personPageNow) {

        Page<Person> personPage = null;
        Pageable pageable = PageRequest.of(personPageNow, 3, Sort.Direction.DESC, "id");
        personPage = checkPage(degree, graduate, pageable);
        return personPage;
    }

    private Page<Person> checkPage(String degree,boolean graduate,Pageable pageable) {
        Page<Person> personPage = null;
        if ("professor".equals(degree) || "".equals(degree)) {
            personPage = personRepository.findByDegreeOrDegree(Degree.PROFESSOR, Degree.VISITING_PROFESSOR, pageable);
        } else if ("PHD".equals(degree)) {
            personPage = personRepository.findByDegreeAndGraduated(Degree.PHD, graduate, pageable);
        } else if ("master".equals(degree)) {
            personPage = personRepository.findByDegreeAndGraduated(Degree.MASTER, graduate, pageable);
        } else if ("undergraduate".equals(degree)) {
            personPage = personRepository.findByDegreeAndGraduated(Degree.UNDERGRADUATE, graduate, pageable);
        }
        return personPage;
    }
}
