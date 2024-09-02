package com.spring.rest.customer_rest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created")
    private Long created;

    @Column(name = "updated")
    private Long updated;

    @NotEmpty
    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Email
    @NotEmpty
    @Size(min = 2, max = 100)
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Pattern(regexp = "\\+\\d{6,14}", message = "Phone must start with '+' and contain only digits, length 6 to 14")
    @Column(name = "phone", nullable = true, length = 14)
    private String phone;


    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    public Customer() {
    }

    public Customer(Boolean isActive, String phone, String email, String fullName, Long updated, Long created) {
        this.isActive = isActive;
        this.phone = phone;
        this.email = email;
        this.fullName = fullName;
        this.updated = updated;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
