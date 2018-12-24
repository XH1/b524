package com.socog.website.module.direction.repository;

import com.socog.website.module.direction.entity.Direction;
import com.socog.website.module.paper.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qixiaohui
 * @descreption
 * @date 2018/12/23
 */

public interface DirectionRepository extends JpaRepository<Direction,Integer> {
    Page<Paper> findByName(String name,Pageable pageable);
}
