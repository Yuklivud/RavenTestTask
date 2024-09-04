package raven_task.service;

import raven_task.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public CustomerDTO getCustomerById(int id);

    public List<CustomerDTO> getAllCustomers();

    public CustomerDTO createCustomer(CustomerDTO customerDTO);

    public void deleteCustomerById(int id);

    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO);

    public boolean emailExists(String email);
}
