package com.ProjectService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ProjectService.entity.Project;

@Repository		// Marks this interface as a Spring Data repository
public interface ProjectRepo extends JpaRepository<Project,Long> {
}
