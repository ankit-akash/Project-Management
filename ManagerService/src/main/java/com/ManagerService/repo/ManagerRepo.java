package com.ManagerService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ManagerService.entity.Manager;
import com.google.common.base.Optional;

@Repository			// Marks this interface as a Spring Data repository
public interface ManagerRepo extends JpaRepository<Manager,Long> {
}

