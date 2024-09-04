package com.spring.rest.customer_rest.service;

import com.spring.rest.customer_rest.dao.CustomerDAO;
import com.spring.rest.customer_rest.dto.CustomerDTO;
import com.spring.rest.customer_rest.entity.Customer;
import com.spring.rest.customer_rest.exceptions.EmailAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(customer == null) {
            throw new EntityNotFoundException();
        }
        return customerDTOMapper.entityToDTO(customer);
    }

    @Override
    @Transactional
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        if(customers.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return customers.stream().map(customerDTOMapper::entityToDTO).toList();
    }

    @Override
    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if(emailExists(customerDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists " + customerDTO.getEmail());
        }
        Customer customer = customerDTOMapper.dtoToEntity(customerDTO);

        return customerDTOMapper.entityToDTO(customerDAO.createCustomer(customer));
    }

    @Override
    @Transactional
    public void deleteCustomerById(int id) {
        if(customerDAO.getCustomerById(id) == null) {
            throw new EntityNotFoundException();
        }
        customerDAO.deleteCustomerById(id);
    }

    @Override
    @Transactional
    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        Customer customer = customerDAO.getCustomerById(id);
        if(customer == null) {
            throw new EntityNotFoundException();
        }
        Customer customerFromDTO = customerDTOMapper.dtoToEntity(customerDTO);

        customer.setFullName(customerFromDTO.getFullName());
        customer.setPhone(customerFromDTO.getPhone());

        return customerDTOMapper.entityToDTO(customerDAO.updateCustomer(id, customer));
    }

    @Override
    @Transactional
    public boolean emailExists(String email) {
        return customerDAO.emailExists(email);
    }
}
