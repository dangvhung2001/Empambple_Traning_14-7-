package com.example.thymleaf_trainingday5.controller;

import com.example.thymleaf_trainingday5.domain.Employee;
import com.example.thymleaf_trainingday5.domain.Role;
import com.example.thymleaf_trainingday5.repository.RoleRepository;
import com.example.thymleaf_trainingday5.service.EmployeeService;
import com.example.thymleaf_trainingday5.service.dto.EmployeeDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class AuthController {
    private final EmployeeService employeeService;
    private final RoleRepository roleRepository;

    public AuthController(EmployeeService employeeService, RoleRepository roleRepository) {
        this.employeeService = employeeService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        model.addAttribute("employees", employeeDTO);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeDTO);
            return "/registration";
        }
        employeeService.save(employeeDTO);
        return "redirect:/registration?success";
    }
}
