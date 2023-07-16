package com.example.thymleaf_trainingday5.repository;

import com.example.thymleaf_trainingday5.domain.Role;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> { }