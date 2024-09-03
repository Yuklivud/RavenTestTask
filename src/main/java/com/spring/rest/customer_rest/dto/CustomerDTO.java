package com.spring.rest.customer_rest.dto;

import jakarta.validation.constraints.*;

public class CustomerDTO {

    private Long id;

    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 50)
    private String fullName;

    @NotBlank
    @NotEmpty
    @Email
    @Size(min = 2, max = 100)
    private String email;

    @Pattern(regexp = "\\+\\d{6,14}", message = "Phone must start with '+' and contain only digits, length 6 to 14")
    private String phone;

    public CustomerDTO(Long id, String fullName, String email, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public CustomerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
