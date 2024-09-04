package com.spring.rest.customer_rest.service;

import com.spring.rest.customer_rest.dao.CustomerDAO;
import com.spring.rest.customer_rest.dto.CustomerDTO;
import com.spring.rest.customer_rest.entity.Customer;
import com.spring.rest.customer_rest.exceptions.EmailAlreadyExistsException;
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
        Customer customer = customerDTOMapper.dtoToEntity(customerDTO);

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
