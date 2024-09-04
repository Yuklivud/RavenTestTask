package raven_task.mapper;

import raven_task.dto.CustomerDTO;
import raven_task.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerDTOMapper {

    public CustomerDTO entityToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setFullName(customer.getFullName());
        return dto;
    }

    public Customer dtoToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setFullName(customerDTO.getFullName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        return customer;
    }
}

