package com.socog.website.module.paper.repository;

import com.socog.website.module.paper.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PaperRepository extends JpaRepository<Paper, Integer> {
    //paperlist页面所需
    Page<Paper> findByYear(String year, Pageable pageable);
    Page<Paper> findByType(String type, Pageable pageable);
    Page<Paper> findByArea(String area, Pageable pageable);//paper_area也需要
    @Query(value = "select type,count(type) as counts from paper group by type", nativeQuery = true)
    List<Map<String,Object>> groupByType();
    @Query(value = "select year,count(year) as counts from paper group by year DESC", nativeQuery = true)
    List<Map<String,Object>> groupByYear();
    @Query(value = "select area,count(area) as counts from paper group by area", nativeQuery = true)
    List<Map<String,Object>> groupByArea();



    //***************************************************************
    //paper_area页面所需
   // Page<Paper> findByArea(String area, Pageable pageable);
    Page<Paper> findByAreaAndYear(String area, String year, Pageable pageable);
    @Query(value = "select year,count(year) as counts from paper where area=?1 group by year DESC ", nativeQuery = true)
    List<Map<String,Object>> groupByYear1(String area);
}
