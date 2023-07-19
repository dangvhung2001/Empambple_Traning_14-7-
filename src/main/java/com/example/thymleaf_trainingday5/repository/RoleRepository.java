package com.example.thymleaf_trainingday5.repository;

import com.example.thymleaf_trainingday5.domain.Role;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {
    Role findByName(String email);
}