package com.management.pizzaria.services;

import com.management.pizzaria.dtos.AddressDTO;
import com.management.pizzaria.models.ViaCepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    @Autowired
    private RestTemplate restTemplate;

    public ViaCepResponse findCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json";
        return restTemplate.getForObject(url, ViaCepResponse.class);
    }
}
