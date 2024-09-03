package com.spring.rest.customer_rest.service;

import com.spring.rest.customer_rest.entity.Customer;

import java.util.List;

public interface CustomerService {
    public Customer getCustomerById(int id);
    public List<Customer> getAllCustomers();
    public Customer createCustomer(Customer customer);
    public void deleteCustomerById(int id);
    public Customer updateCustomer(int id, Customer customer);
    public boolean emailExists(String email);
}
