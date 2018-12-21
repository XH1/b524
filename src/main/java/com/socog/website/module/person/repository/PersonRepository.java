package com.socog.website.module.person.repository;

import com.socog.website.module.person.entiy.Degree;
import com.socog.website.module.person.entiy.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xinghao
 * @descreption
 * @date 2018/12/19
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByDegree(Degree degree, Pageable pageable);

    List<Person> findByDegreeAndGraduated(Degree degree,boolean graduated,Pageable pageable);
}
