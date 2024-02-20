package com.management.pizzaria.services;

import com.management.pizzaria.controllers.CustomerController;
import com.management.pizzaria.dtos.CustomerDTO;
import com.management.pizzaria.exceptions.CustomerNotFoundException;
import com.management.pizzaria.models.Customer;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.management.pizzaria.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    public Customer createCustomer(CustomerDTO customerDTO) throws Exception {
        Customer newCustomer = new Customer(customerDTO);
        this.saveCustomer(newCustomer);
        newCustomer.add(linkTo(methodOn(CustomerController.class).saveCustomer(customerDTO)).withSelfRel());
        newCustomer.add(linkTo(methodOn(CustomerController.class).getCustomerByName(customerDTO.name())).withRel("Find By Name"));
        newCustomer.add(linkTo(methodOn(CustomerController.class).findCustomerById(newCustomer.getKey())).withRel("Find By Id"));
        newCustomer.add(linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("Find All"));
        newCustomer.add(linkTo(methodOn(CustomerController.class).updateCustomer(newCustomer.getKey(), newCustomer)).withRel("Update Customer"));
        newCustomer.add(linkTo(methodOn(CustomerController.class).deleteCustomer(newCustomer.getKey())).withRel("Delete Customer"));
        return newCustomer;
    }

    public List<Customer> allCustomers() {
        var customer = customerRepository.findAll();
        customer
                .forEach(c -> {
                    try {
                        c.add(linkTo(methodOn(CustomerController.class).findCustomerById(c.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return customer;
    }

    public Customer getCustomerById(Long id) throws Exception {
        var customer = this.customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer with ID provided not found!"));
        customer.add(linkTo(methodOn(CustomerController.class).findCustomerById(id)).withSelfRel());
        customer.add(linkTo(methodOn(CustomerController.class).getCustomerByName(customer.getName())).withRel("Find By Name"));
        customer.add(linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("Find all"));
        customer.add(linkTo(methodOn(CustomerController.class).updateCustomer(id, customer)).withRel("Update Customer"));
        customer.add(linkTo(methodOn(CustomerController.class).deleteCustomer(id)).withRel("Delete Customer"));
        return customer;
    }
    public Customer getCustomerByName(String name) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = Optional.ofNullable(this.customerRepository.findByName(name));
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            throw new CustomerNotFoundException("Customer with name provided not found");
        }
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

        existCustomer.add(linkTo(methodOn(CustomerController.class).findCustomerById(id)).withSelfRel());

        return customerRepository.save(existCustomer);
    }

    public ResponseEntity<Customer> deleteCustomer(Long id) {

        if (customerRepository.findById(id).isPresent()) {
            this.customerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

