package com.example.thymleaf_trainingday5.service;

import com.example.thymleaf_trainingday5.service.dto.DepartmentDTO;
import org.springframework.data.domain.*;

import java.util.*;

public interface DepartmentService {
    DepartmentDTO save(DepartmentDTO departmentDTO);

    Page<DepartmentDTO> findAll(Pageable pageable);

    Optional<DepartmentDTO> findOne(Long id);

    void delete(Long id);
    List<DepartmentDTO> getAll();
}
