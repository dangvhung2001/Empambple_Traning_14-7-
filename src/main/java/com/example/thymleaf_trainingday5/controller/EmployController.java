package com.example.thymleaf_trainingday5.controller;

import com.example.thymleaf_trainingday5.service.DepartmentService;
import com.example.thymleaf_trainingday5.service.EmployeeService;
import com.example.thymleaf_trainingday5.service.dto.DepartmentDTO;
import com.example.thymleaf_trainingday5.service.dto.EmployeeDTO;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/employees")
public class EmployController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/show")
    public String searchEmployees(@RequestParam(required = false) String textSearch, Pageable pageable, Model model) {
        Page<EmployeeDTO> employees = employeeService.findAll(textSearch, pageable);
        model.addAttribute("employees", employees);
        return "employee/index";
    }

    @GetMapping("/")
    public String findAllEmployees(Pageable pageable, Model model) {
        Page<EmployeeDTO> employees = employeeService.findAllEmployee(pageable);
        model.addAttribute("employees", employees);
        return "employee/index";
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        List<DepartmentDTO> departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        return "employee/add";
    }

    @PostMapping("/add")
    public String doAdd(@ModelAttribute("employee") @Valid EmployeeDTO employeeDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "employee/add";
        }
        employeeService.save(employeeDTO);
        return "redirect:/employees/";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Long id, Model model
    ) {
        Optional<EmployeeDTO> employee = employeeService.findOne(id);
        if (employee.isPresent()) {
            EmployeeDTO employeeDTO = employee.get();
            model.addAttribute("employee", employeeDTO);
            List<DepartmentDTO> departments = departmentService.getAll();
            model.addAttribute("departments", departments);
            return "employee/edit";
        } else {
            return "redirect:/employees/";
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Long id, @ModelAttribute("employee") @Valid EmployeeDTO employeeDTO,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "employee/edit";
        }
        employeeDTO.setId(id);
        employeeService.save(employeeDTO);
        return "redirect:/employees/";
    }

    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employees/";
    }
}
