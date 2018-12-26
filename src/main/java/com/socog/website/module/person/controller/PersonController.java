package com.socog.website.module.person.controller;

import com.socog.website.module.person.entiy.Degree;
import com.socog.website.module.person.entiy.Person;
import com.socog.website.module.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

/*    @GetMapping("/personlist")
    public String getAll(@RequestParam(defaultValue = "") String type, @RequestParam(defaultValue = "false") boolean graduate, Model model){

        *//*List<Person> personList = new ArrayList<>();

        if (type == null || "".equals(type) || "all".equals(type)) {
            personList = personRepository.findAll();
        } else if ("professor".equals(type)) {
            personList = personRepository.findByDegree(Degree.PROFESSOR);
            personList.addAll(personRepository.findByDegree(Degree.VISITING_PROFESSOR));
        } else if ("PHD".equals(type)) {
            personList = personRepository.findByDegreeAndGraduated(Degree.PHD,graduate);
        } else if ("master".equals(type)) {
            personList = personRepository.findByDegreeAndGraduated(Degree.MASTER,graduate);
        } else if ("undergraduate".equals(type)) {
            personList = personRepository.findByDegreeAndGraduated(Degree.UNDERGRADUATE,graduate);
        }
        model.addAttribute("personList", personList);
        return "personlist";*//*
    }*/
}
