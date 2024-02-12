package com.management.pizzaria.services;

import com.management.pizzaria.dtos.PizzaDTO;
import com.management.pizzaria.models.Pizza;
import com.management.pizzaria.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public void savePizza(Pizza pizza) {
        this.pizzaRepository.save(pizza);
    }

    public Pizza createPizza(PizzaDTO pizzaDTO) {
        Pizza newPizza = new Pizza(pizzaDTO);
        this.savePizza(newPizza);
        return newPizza;
    }

    public List<Pizza> getAllPizzas() {
        return this.pizzaRepository.findAll();
    }

    public Pizza getPizzaById(Long id) throws Exception{
        return this.pizzaRepository.findById(id).orElseThrow(() -> new Exception("Pizza with ID provided not found"));
    }

    public Pizza updatePizza(Long id, Pizza pizza) throws Exception{
        Pizza existPizza = this.pizzaRepository.findById(id).orElseThrow(() -> new Exception("Pizza with ID provided not found"));
        existPizza.setFlavor(pizza.getFlavor());
        existPizza.setIngredients(pizza.getIngredients());

        return this.pizzaRepository.save(existPizza);
    }

    public void deletePizza(Long id) {
        try {
            this.pizzaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
