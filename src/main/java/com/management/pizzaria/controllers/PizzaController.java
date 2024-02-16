package com.management.pizzaria.controllers;

import com.management.pizzaria.dtos.PizzaDTO;
import com.management.pizzaria.models.Pizza;
import com.management.pizzaria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @PostMapping
    public ResponseEntity<Pizza> createPizza(@RequestBody PizzaDTO pizzaDTO) {
        Pizza newPizza = this.pizzaService.createPizza(pizzaDTO);
        return new ResponseEntity<>(newPizza, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pizza>> getAllPizzas() {
        var allPizzas = this.pizzaService.getAllPizzas();
        return new ResponseEntity<>(allPizzas, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable (value = "id") Long id) throws Exception {
        var pizza = this.pizzaService.getPizzaById(id);
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable (value = "id") Long id, @RequestBody Pizza pizza) throws Exception {
        var pizzaUpdate = this.pizzaService.updatePizza(id, pizza);
        return new ResponseEntity<>(pizzaUpdate, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pizza> deletePizza(@PathVariable (value = "id") Long id) {
        return this.pizzaService.deletePizza(id);
    }
}
