package com.spring.rest.customer_rest.dao;

import com.spring.rest.customer_rest.entity.Customer;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Customer getCustomerById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Customer.class, id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Customer", Customer.class).list();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Session session = entityManager.unwrap(Session.class);
        customer.setCreated(System.currentTimeMillis());
        session.save(customer);
        return customer;
    }

    @Override
    public void deleteCustomerById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Customer customer = session.get(Customer.class, id);
        customer.setActive(false);
        customer.setUpdated(System.currentTimeMillis());
        session.save(customer);
    }

    @Override
    public Customer updateCustomer(int id, Customer customer) {
        Session session = entityManager.unwrap(Session.class);
        customer.setUpdated(System.currentTimeMillis());
        session.update(customer);
        return customer;
    }

    @Override
    public boolean emailExists(String email) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "SELECT COUNT(c) FROM Customer c WHERE c.email = :email";
        Long count = (Long) session.createQuery(hql)
                .setParameter("email", email)
                .uniqueResult();
        return count != null && count > 0;
    }
}
