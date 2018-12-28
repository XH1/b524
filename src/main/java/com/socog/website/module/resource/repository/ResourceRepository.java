package com.socog.website.module.resource.repository;

import com.socog.website.module.resource.entity.Resource;
import com.socog.website.module.resource.entity.ResourceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xinghao
 * @descreption
 * @date 2018/12/26
 */

public interface ResourceRepository extends JpaRepository<Resource,Integer> {
    Page<Resource> findByResourceType(ResourceType resourceType, Pageable pageable);
}
