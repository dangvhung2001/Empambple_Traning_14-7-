package com.example.thymleaf_trainingday5.service.mapper.impl;

import com.example.thymleaf_trainingday5.domain.*;
import com.example.thymleaf_trainingday5.service.dto.EmployeeDTO;
import com.example.thymleaf_trainingday5.service.mapper.EmployeeMapper;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class EmployeeMapperImpl implements EmployeeMapper {
    @Override
    public Employee toEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPassword(dto.getPassword());
        employee.setDepartmentId(dto.getDepartmentId());
        return employee;
    }

    @Override
    public EmployeeDTO toDto(Employee entity) {
        if (entity == null) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(entity.getId());
        employeeDTO.setName(entity.getName());
        employeeDTO.setEmail(entity.getEmail());
        employeeDTO.setPassword(entity.getPassword());
        employeeDTO.setDepartmentId(entity.getDepartmentId());

        Department department = entity.getDepartment();
        if (department != null) {
            employeeDTO.setDepartmentName(department.getName());
        }

        Set<Role> roles = entity.getRoles();
        if (roles != null) {
            employeeDTO.setRoles(roles.stream().map(Role::getId).collect(Collectors.toSet()));
        }
        return employeeDTO;
    }

    @Override
    public List<Employee> toEntity(List<EmployeeDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<Employee> list = new ArrayList<Employee>(dtoList.size());
        for (EmployeeDTO employeeDTO : dtoList) {
            list.add(toEntity(employeeDTO));
        }

        return list;
    }

    @Override
    public List<EmployeeDTO> toDto(List<Employee> entityList) {
        if (entityList == null) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>(entityList.size());
        for (Employee employee : entityList) {
            list.add(toDto(employee));
        }

        return list;
    }
}
