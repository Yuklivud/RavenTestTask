package com.spring.rest.customer_rest.service;

import com.spring.rest.customer_rest.dao.CustomerDAO;
import com.spring.rest.customer_rest.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    @Transactional
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerDAO.createCustomer(customer);
    }

    @Override
    @Transactional
    public void deleteCustomerById(int id) {
        customerDAO.deleteCustomerById(id);
    }

    @Override
    @Transactional
    public void updateCustomer(int id, Customer customer) {
        customerDAO.updateCustomer(id, customer);
    }

    @Override
    @Transactional
    public boolean emailExists(String email) {
        return customerDAO.emailExists(email);
    }
}
