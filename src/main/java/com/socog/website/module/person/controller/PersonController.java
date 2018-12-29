package com.socog.website.module.person.controller;

import com.socog.website.module.person.entiy.Degree;
import com.socog.website.module.person.entiy.Person;
import com.socog.website.module.person.repository.PersonRepository;
//import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/personlist")
    public String getAll(@RequestParam(defaultValue = "PROFESSOR") Degree degree, @RequestParam(defaultValue = "false") boolean graduate,
                         @RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        Page<Person> personPage = checkPage(degree,graduate,pageable);
        model.addAttribute("personPage", personPage);
        model.addAttribute("degree", degree);
        model.addAttribute("graduate", graduate);
        return "personlist";
    }

    @GetMapping("/person")
    public String getOne(@RequestParam int id, @RequestParam(defaultValue = "all") String year,  Model model) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (!personOptional.isPresent()) {
            return "error";
        } else {
            Person person = personOptional.get();
            model.addAttribute("person", person);

            List<Map<String,Object>> countByYear = personRepository.findCountByDate(id);
            model.addAttribute("countByYear",countByYear);
            model.addAttribute("year", year);
            return "person";
        }
    }

    @ResponseBody
    @GetMapping("/personpage")
    public Page<Person> getPersonPage(@RequestParam(defaultValue = "PROFESSOR") Degree degree, @RequestParam(defaultValue = "false") boolean graduate,
                                      @RequestParam(defaultValue = "0") int personPageNow) {
        Pageable pageable = PageRequest.of(personPageNow, 3, Sort.Direction.DESC, "id");
        return checkPage(degree, graduate, pageable);
    }

    private Page<Person> checkPage(Degree degree,boolean graduate,Pageable pageable) {
        Page<Person> personPage;
        if (Degree.PROFESSOR==degree) {
            personPage = personRepository.findByDegreeOrDegree(Degree.PROFESSOR, Degree.VISITING_PROFESSOR, pageable);
        } else {
            personPage = personRepository.findByDegreeAndGraduated(degree, graduate, pageable);
        }
        return personPage;
    }
}
