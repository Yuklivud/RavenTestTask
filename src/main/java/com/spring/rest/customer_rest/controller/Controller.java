package com.spring.rest.customer_rest.controller;

import com.spring.rest.customer_rest.dto.CustomerDTO;
import com.spring.rest.customer_rest.entity.Customer;
import com.spring.rest.customer_rest.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/api/customers")
@Validated
public class Controller {

    private final CustomerService customerService;

    @Autowired
    public Controller(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOs = customers.stream().map(c -> new CustomerDTO(c.getId(), c.getFullName(), c.getEmail(), c.getPhone())).toList();
        return ResponseEntity.ok(customerDTOs);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody Customer customer) {
        if (customerService.emailExists(customer.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        Customer createdCustomer = new Customer();
        createdCustomer.setFullName(customer.getFullName());
        createdCustomer.setEmail(customer.getEmail());
        createdCustomer.setPhone(customer.getPhone());

        customerService.createCustomer(createdCustomer);

        CustomerDTO dto  = new CustomerDTO(customer.getId(), customer.getFullName(), customer.getEmail(), customer.getPhone());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable int id, @Valid @RequestBody Customer customer) {
        Customer existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer == null) {
            return ResponseEntity.notFound().build();
        }
        if(customer.getFullName() != null && !customer.getFullName().isEmpty()) {
            existingCustomer.setFullName(customer.getFullName());
        }
        if (customer.getPhone() != null && !customer.getPhone().isEmpty()) {
            existingCustomer.setPhone(customer.getPhone());
        }
        customerService.updateCustomer(id, existingCustomer);

        CustomerDTO dto = new CustomerDTO(customer.getId(), customer.getFullName(), existingCustomer.getEmail(), customer.getPhone());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id) {
        Customer customer = customerService.getCustomerById(id);

        if(customer != null) {
            CustomerDTO dto = new CustomerDTO(
                    customer.getId(),
                    customer.getFullName(),
                    customer.getEmail(),
                    customer.getPhone()
            );
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable int id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}
