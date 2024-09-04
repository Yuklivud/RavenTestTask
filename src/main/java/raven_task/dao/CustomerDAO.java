package raven_task.dao;

import raven_task.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    public Customer getCustomerById(int id);

    public List<Customer> getAllCustomers();

    public Customer createCustomer(Customer customer);

    public void deleteCustomerById(int id);

    public Customer updateCustomer(int id, Customer customer);

    public boolean emailExists(String email);
}
