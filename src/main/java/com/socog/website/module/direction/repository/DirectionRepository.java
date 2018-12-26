package com.socog.website.module.direction.repository;

import com.socog.website.module.direction.entity.Direction;
import com.socog.website.module.paper.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author qixiaohui
 * @descreption
 * @date 2018/12/23
 */

public interface DirectionRepository extends JpaRepository<Direction,Integer> {
    /*Page<Paper> findByArea(String area, Pageable pageable);
    Page<Paper> findByAreaAndYear(String area, String year, Pageable pageable);
    @Query(value = "select year,count(year) as counts from paper where area=#{area} group by year DESC ", nativeQuery = true)
    List<Map<String,Object>> groupByYear(String area);*/
}
