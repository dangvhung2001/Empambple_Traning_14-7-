package com.example.thymleaf_trainingday5.service.impl;

import com.example.thymleaf_trainingday5.domain.*;
import com.example.thymleaf_trainingday5.repository.*;
import com.example.thymleaf_trainingday5.service.EmployeeService;
import com.example.thymleaf_trainingday5.service.dto.EmployeeDTO;
import com.example.thymleaf_trainingday5.service.mapper.EmployeeMapper;
import com.example.thymleaf_trainingday5.util.TbConstants;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               RoleRepository roleRepository,
                               EmployeeMapper employeeMapper,
                               PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null){
            role = roleRepository.save(new Role(TbConstants.Roles.USER));
        }
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        employee.setRoles(roles);
        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));

        Employee employee = new Employee(employeeDTO.getId(),employeeDTO.getName(), employeeDTO.getEmail(), passwordEncoder.encode(employeeDTO.getPassword()),
                Arrays.asList(role));
        employeeRepository.save(employee);
    }

    @Override
    public Page<EmployeeDTO> findAll(String textSearch, Pageable pageable) {
        return employeeRepository.findAllByNameOrEmailContainingIgnoreCase(textSearch, textSearch, pageable).map(employeeMapper::toDto);
    }

    @Override
    public Optional<EmployeeDTO> findOne(Long id) {
        return employeeRepository.findById(id).map(employeeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<EmployeeDTO> findAllEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(employeeMapper::toDto);
    }

    @Override
    public Optional<EmployeeDTO> findByEmail(String email) {
        return employeeRepository.findOneByEmailIgnoreCase(email).map(employeeMapper::toDto);
    }

    @Override
    public Employee findUserByEmail(String email) {
        return employeeRepository.findEmployeeByEmail(email);
    }
}
