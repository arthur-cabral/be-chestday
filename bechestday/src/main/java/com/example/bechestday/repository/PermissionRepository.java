package com.example.bechestday.repository;

import com.example.bechestday.model.Exercise;
import com.example.bechestday.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
