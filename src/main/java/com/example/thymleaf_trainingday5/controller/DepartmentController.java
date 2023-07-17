package com.example.thymleaf_trainingday5.controller;

import com.example.thymleaf_trainingday5.repository.DepartmentRepository;
import com.example.thymleaf_trainingday5.service.DepartmentService;
import com.example.thymleaf_trainingday5.service.dto.DepartmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentService departmentService, DepartmentRepository departmentRepository) {
        this.departmentService = departmentService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/")
    public String findAll(Model model, Pageable pageable) {
        Page<DepartmentDTO> departments = departmentService.findAll(pageable);
        model.addAttribute("departments", departments);
        return "department/detail";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("department", new DepartmentDTO());
        return "department/add";
    }
    @PostMapping("/createDepartment")
    public String createDepartment(@ModelAttribute DepartmentDTO departmentDTO) {
        departmentService.save(departmentDTO);
        return "redirect:/departments/";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        DepartmentDTO department = departmentService.findOne(id).get();
        model.addAttribute("department", department);
        return "department/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute DepartmentDTO departmentDTO) {
        if (!departmentRepository.existsById(id)) {
            System.out.println("erro");;
        }
        departmentService.save(departmentDTO);
        return "redirect:/departments/";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return "redirect:/departments/";
    }
}