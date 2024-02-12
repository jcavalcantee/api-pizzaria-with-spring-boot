package com.management.pizzaria.services;

import com.management.pizzaria.dtos.CustomerDTO;
import com.management.pizzaria.models.Customer;
import com.management.pizzaria.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer newCustomer = new Customer(customerDTO);
        this.saveCustomer(newCustomer);
        return newCustomer;
    }

    public List<Customer> allCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) throws Exception{
        return this.customerRepository.findById(id).orElseThrow(
                () -> new Exception("Customer with ID provided not found!"));
    }

    public Customer updateCustomer(Long id, Customer customer) throws Exception{
        Customer existCustomer = customerRepository.findById(id).orElseThrow(
                () -> new Exception("Customer with ID provided not found!"));
        existCustomer.setName(customer.getName());
        existCustomer.setTelphone(customer.getTelphone());
        existCustomer.setZipCode(customer.getZipCode());
        existCustomer.setStreet(customer.getStreet());
        existCustomer.setDistrict(customer.getDistrict());
        existCustomer.setNumber(customer.getNumber());

        return customerRepository.save(existCustomer);
    }

    public void deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
