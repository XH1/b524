package com.socog.website.module.project.repository;

import com.socog.website.module.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}

