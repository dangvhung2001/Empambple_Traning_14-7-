package com.example.thymleaf_trainingday5.service;

import com.example.thymleaf_trainingday5.domain.Employee;
import com.example.thymleaf_trainingday5.service.dto.DepartmentDTO;
import com.example.thymleaf_trainingday5.service.dto.EmployeeDTO;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDTO save(EmployeeDTO notifyDTO);

    Page<EmployeeDTO> findAll(String textSearch, Pageable pageable);

    Optional<EmployeeDTO> findOne(Long id);

    void delete(Long id);

    Page<EmployeeDTO> findAllEmployee(Pageable pageable);
    Optional<EmployeeDTO> findByEmail(String email);

    void saveEmployee(EmployeeDTO employeeDTO);
    Employee findUserByEmail(String email);
    List<EmployeeDTO> getAll();
}
