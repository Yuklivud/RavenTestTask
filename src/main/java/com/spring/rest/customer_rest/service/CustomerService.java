package com.spring.rest.customer_rest.service;

import com.spring.rest.customer_rest.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public CustomerDTO getCustomerById(int id);
    public List<CustomerDTO> getAllCustomers();
    public CustomerDTO createCustomer(CustomerDTO customerDTO);
    public void deleteCustomerById(int id);
    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO);
    public boolean emailExists(String email);
}
