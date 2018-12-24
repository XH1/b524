package com.socog.website.module.person.repository;

import com.socog.website.module.person.entiy.Degree;
import com.socog.website.module.person.entiy.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xinghao
 * @descreption
 * @date 2018/12/19
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Page<Person> findByDegreeOrDegree(Degree degree1,Degree degree2,Pageable pageable);

    Page<Person> findByDegreeAndGraduated(Degree degree,boolean graduated,Pageable pageable);

}
