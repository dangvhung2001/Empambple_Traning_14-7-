package com.example.thymleaf_trainingday5.repository;

import com.example.thymleaf_trainingday5.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    Optional<Employee> findOneByEmailIgnoreCase(String email);

    Page<Employee> findAllByNameOrEmailContainingIgnoreCase(String textSearchName, String textSearchEmail, Pageable pageable);
}

