package com.management.pizzaria.controllers;

import com.management.pizzaria.dtos.AddressDTO;
import com.management.pizzaria.dtos.AddressRequestDTO;
import com.management.pizzaria.models.Address;
import com.management.pizzaria.models.ViaCepResponse;
import com.management.pizzaria.services.AddressService;
import com.management.pizzaria.services.ViaCepService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepResponse> findAddress(@PathVariable (value = "cep") String cep) {
        var response = viaCepService.findCep(cep);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveAddress(@RequestBody AddressRequestDTO request) throws Exception {
        var response = addressService.saveAddress(request.getCep(), request.getNumero(), request.getId());
        return new ResponseEntity<String>(String.valueOf(response), HttpStatus.CREATED);

    }
}
