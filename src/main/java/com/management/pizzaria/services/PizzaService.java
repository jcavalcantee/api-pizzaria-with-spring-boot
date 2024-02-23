package com.management.pizzaria.services;

import com.management.pizzaria.controllers.PizzaController;
import com.management.pizzaria.dtos.PizzaDTO;
import com.management.pizzaria.exceptions.ProductNotFoundException;
import com.management.pizzaria.models.Pizza;
import com.management.pizzaria.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PizzaService extends ProductService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public void savePizza(Pizza pizza) {
        this.pizzaRepository.save(pizza);
    }

    public Pizza createPizza(PizzaDTO pizzaDTO) throws Exception {
        Pizza newPizza = new Pizza(pizzaDTO);
        this.savePizza(newPizza);
        newPizza.add(linkTo(methodOn(PizzaController.class).createPizza(pizzaDTO)).withSelfRel());
        newPizza.add(linkTo(methodOn(PizzaController.class).getPizzaByFlavor(pizzaDTO.flavor())).withRel("Find By Flavor"));
        newPizza.add(linkTo(methodOn(PizzaController.class).getPizzaById(newPizza.getId())).withRel("Find By ID"));
        newPizza.add(linkTo(methodOn(PizzaController.class).getAllPizzas()).withRel("Find All"));
        newPizza.add(linkTo(methodOn(PizzaController.class).updatePizza(newPizza.getId(), newPizza)).withRel("Update Pizza"));
        newPizza.add(linkTo(methodOn(PizzaController.class).deletePizza(newPizza.getId())).withRel("Delete Pizza"));
        return newPizza;
    }

    public List<Pizza> getAllPizzas() {
        var pizza = this.pizzaRepository.findAll();
        pizza
                .forEach(p -> {
                    p.add(linkTo(methodOn(PizzaController.class).getAllPizzas()).withSelfRel());
                    try {
                        p.add(linkTo(methodOn(PizzaController.class).getPizzaByFlavor(p.getFlavor())).withRel("Find By Flavor"));
                        p.add(linkTo(methodOn(PizzaController.class).getPizzaById(p.getId())).withRel("Find By ID"));
                        p.add(linkTo(methodOn(PizzaController.class).updatePizza(p.getId(), new Pizza())).withRel("Update Pizza"));
                        p.add(linkTo(methodOn(PizzaController.class).deletePizza(p.getId())).withRel("Delete Pizza"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return pizza;
    }

    public Pizza getPizzaById(Long id) throws Exception {
        var pizza = this.pizzaRepository.findById(id).orElseThrow(() -> new Exception("Pizza with ID provided not found"));
        pizza.add(linkTo(methodOn(PizzaController.class).getPizzaById(id)).withSelfRel());
        pizza.add(linkTo(methodOn(PizzaController.class).getPizzaByFlavor(pizza.getFlavor())).withRel("Find By Flavor"));
        pizza.add(linkTo(methodOn(PizzaController.class).getAllPizzas()).withRel("Find All"));
        pizza.add(linkTo(methodOn(PizzaController.class).updatePizza(id, pizza)).withRel("Update Pizza"));
        pizza.add(linkTo(methodOn(PizzaController.class).deletePizza(id)).withRel("Delete Pizza"));
        return pizza;
    }

    public Pizza getPizzaByFlavor(String flavor) throws Exception {
        var pizza = this.pizzaRepository.findByFlavor(flavor);
        if (pizza != null) {
            pizza.add(linkTo(methodOn(PizzaController.class).getPizzaByFlavor(flavor)).withSelfRel());
            pizza.add(linkTo(methodOn(PizzaController.class).getPizzaById(pizza.getId())).withRel("Find By ID"));
            pizza.add(linkTo(methodOn(PizzaController.class).getAllPizzas()).withRel("Find All"));
            pizza.add(linkTo(methodOn(PizzaController.class).updatePizza(pizza.getId(), pizza)).withRel("Update Pizza"));
            pizza.add(linkTo(methodOn(PizzaController.class).deletePizza(pizza.getId())).withRel("Delete Pizza"));
            return pizza;
        } else {
            throw new ProductNotFoundException("Pizza with flavor provided not found");
        }
    }

    public Pizza updatePizza(Long id, Pizza pizza) throws Exception{
        Pizza existPizza = this.pizzaRepository.findById(id).orElseThrow(() -> new Exception("Pizza with ID provided not found"));
        existPizza.setFlavor(pizza.getFlavor());
        existPizza.setIngredients(pizza.getIngredients());
        existPizza.setPrice((pizza.getPrice()));

        existPizza.add(linkTo(methodOn(PizzaController.class).updatePizza(id, pizza)).withSelfRel());
        existPizza.add(linkTo(methodOn(PizzaController.class).getPizzaByFlavor(pizza.getFlavor())).withRel("Find By Flavor"));
        existPizza.add(linkTo(methodOn(PizzaController.class).getPizzaById(id)).withRel("Find By ID"));
        existPizza.add(linkTo(methodOn(PizzaController.class).getAllPizzas()).withRel("Find all"));
        existPizza.add(linkTo(methodOn(PizzaController.class).deletePizza(id)).withRel("Delete Pizza"));

        return this.pizzaRepository.save(existPizza);
    }

    public ResponseEntity<Pizza> deletePizza(Long id) {

        if (pizzaRepository.findById(id).isPresent()) {
            this.pizzaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

