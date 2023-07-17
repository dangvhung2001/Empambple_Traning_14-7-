package com.example.thymleaf_trainingday5.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class EmployeeDTO {
    private Long id;
    @NotEmpty(message = "Thiếu name")
    private String name;
    @Email(message = "Email không hợp lệ")
    private String email;

    private Long departmentId;

    private String departmentName;

    private Set<String> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
