package com.spring.rest.customer_rest.service;

import com.spring.rest.customer_rest.dao.CustomerDAO;
import com.spring.rest.customer_rest.dto.CustomerDTO;
import com.spring.rest.customer_rest.entity.Customer;
import com.spring.rest.customer_rest.exceptions.EmailAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private CustomerDTOMapper customerDTOMapper;

    @Override
    @Transactional
    public CustomerDTO getCustomerById(int id) {
        Customer customer = customerDAO.getCustomerById(id);
        return customerDTOMapper.entityToDTO(customer);
    }

    @Override
    @Transactional
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        return customers.stream().map(customerDTOMapper::entityToDTO).toList();
    }

    @Override
    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if(emailExists(customerDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists " + customerDTO.getEmail());
        }
        Customer customer = new Customer();
        customer.setFullName(customerDTO.getFullName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());

        return customerDTOMapper.entityToDTO(customerDAO.createCustomer(customer));
    }

    @Override
    @Transactional
    public void deleteCustomerById(int id) {
        customerDAO.deleteCustomerById(id);
    }

    @Override
    @Transactional
    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        Customer customer = customerDAO.getCustomerById(id);
        customer.setFullName(customerDTO.getFullName());
        customer.setPhone(customerDTO.getPhone());

        return customerDTOMapper.entityToDTO(customerDAO.updateCustomer(id, customer));
    }

    @Override
    @Transactional
    public boolean emailExists(String email) {
        return customerDAO.emailExists(email);
    }
}
