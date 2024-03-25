package com.management.pizzaria.services;

import com.management.pizzaria.dtos.AddressRequestDTO;
import com.management.pizzaria.models.*;
import com.management.pizzaria.repositories.AddressCustomerRepository;
import com.management.pizzaria.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private AddressCustomerRepository addressCustomerRepository;

    public ResponseEntity<String> saveAddress(String cep, String number, Long customerId) throws Exception {

        ViaCepResponse viaCepResponse = viaCepService.findCep(cep);
        Customer customer = customerService.getCustomerById(customerId);

        if(customer != null) {
            Address address = new Address();
            address.setCep(viaCepResponse.getCep());
            address.setLogradouro(viaCepResponse.getLogradouro());
            address.setBairro(viaCepResponse.getBairro());
            address.setLocalidade(viaCepResponse.getCidade());
            address.setUf(viaCepResponse.getEstado());
            address.setNumero(number);
            address.setCustomerId(customerId);

            Address ad = addressRepository.save(address);

            AddressCustomerKey adk = new AddressCustomerKey(customer, ad);
            AddressCustomer ac = new AddressCustomer();
            ac.setId(adk);

            addressCustomerRepository.save(ac);

            return ResponseEntity.ok("Address successfully registered!");
        } else
            throw new Exception("Customer not found with provided Id");
    }
}
