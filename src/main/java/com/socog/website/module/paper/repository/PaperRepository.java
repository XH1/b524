package com.socog.website.module.paper.repository;

import com.socog.website.module.paper.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PaperRepository extends JpaRepository<Paper, Integer> {
    Page<Paper> findByYear(String year, Pageable pageable);
    Page<Paper> findByType(String type, Pageable pageable);
    Page<Paper> findByArea(String area, Pageable pageable);
    @Query(value = "select type,count(type) as counts from paper group by type", nativeQuery = true)
    List<Map<String,Object>> groupByType();
    @Query(value = "select year,count(year) as counts from paper group by year", nativeQuery = true)
    List<Map<String,Object>> groupByYear();
    @Query(value = "select area,count(area) as counts from paper group by area", nativeQuery = true)
    List<Map<String,Object>> groupByArea();


}
