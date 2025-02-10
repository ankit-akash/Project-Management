package com.ManagerService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ManagerService.entity.Manager;

@Repository			// Marks this interface as a Spring Data repository
public interface ManagerRepo extends JpaRepository<Manager,Long> {
}

