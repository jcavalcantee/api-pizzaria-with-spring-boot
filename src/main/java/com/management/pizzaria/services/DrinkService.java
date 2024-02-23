package com.management.pizzaria.services;

import com.management.pizzaria.controllers.DrinkController;
import com.management.pizzaria.controllers.PizzaController;
import com.management.pizzaria.dtos.DrinkDTO;
import com.management.pizzaria.exceptions.ProductNotFoundException;
import com.management.pizzaria.models.Drink;
import com.management.pizzaria.models.Pizza;
import com.management.pizzaria.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DrinkService extends ProductService{

    @Autowired
    private DrinkRepository drinkRepository;

    public void save(Drink drink) {
        this.drinkRepository.save(drink);
    }

    public Drink createDrink(DrinkDTO drinkDTO) throws Exception {
        Drink newDrink = new Drink(drinkDTO);
        this.save(newDrink);
        newDrink.add(linkTo(methodOn(DrinkController.class).createDrink(drinkDTO)).withSelfRel());
        newDrink.add(linkTo(methodOn(DrinkController.class).getDrinkByName(drinkDTO.name())).withRel("Find By Name"));
        newDrink.add(linkTo(methodOn(DrinkController.class).getDrinkByID(newDrink.getId())).withRel("Find By ID"));
        newDrink.add(linkTo(methodOn(DrinkController.class).getAllDrinks()).withRel("Find All"));
        newDrink.add(linkTo(methodOn(DrinkController.class).updateDrink(newDrink.getId(), newDrink)).withRel("Update Drink"));
        newDrink.add(linkTo(methodOn(DrinkController.class).deleteDrinkById(newDrink.getId())).withRel("Delete Drink"));
        return newDrink;
    }

    public List<Drink> getAllDrinks() {
        var drink = this.drinkRepository.findAll();
        drink
        .forEach(d -> {
            d.add(linkTo(methodOn(DrinkController.class).getAllDrinks()).withSelfRel());
            try {
                d.add(linkTo(methodOn(DrinkController.class).getDrinkByName(d.getName())).withRel("Find By Name"));
                d.add(linkTo(methodOn(DrinkController.class).getDrinkByID(d.getId())).withRel("Find By ID"));
                d.add(linkTo(methodOn(DrinkController.class).updateDrink(d.getId(), new Drink())).withRel("Update Drink"));
                d.add(linkTo(methodOn(DrinkController.class).deleteDrinkById(d.getId())).withRel("Delete Drink"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return drink;
    }

    public Drink getDrinkById(Long id) throws Exception {
        var drink = this.drinkRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Drink with ID provided not found"));
        drink.add(linkTo(methodOn(DrinkController.class).getDrinkByID(drink.getId())).withSelfRel());
        drink.add(linkTo(methodOn(DrinkController.class).getDrinkByName(drink.getName())).withRel("Find By Name"));
        drink.add(linkTo(methodOn(DrinkController.class).getAllDrinks()).withRel("Find All"));
        drink.add(linkTo(methodOn(DrinkController.class).updateDrink(drink.getId(), new Drink())).withRel("Update Drink"));
        drink.add(linkTo(methodOn(DrinkController.class).deleteDrinkById(drink.getId())).withRel("Delete Drink"));
        return drink;
    }

    public Drink getDrinkByName(String name) throws Exception {
        var drink = this.drinkRepository.findByName(name);
        if (drink != null) {
            drink.add(linkTo(methodOn(DrinkController.class).getDrinkByName(name)).withSelfRel());
            drink.add(linkTo(methodOn(DrinkController.class).getDrinkByID(drink.getId())).withRel("Find By ID"));
            drink.add(linkTo(methodOn(DrinkController.class).getAllDrinks()).withRel("Find All"));
            drink.add(linkTo(methodOn(DrinkController.class).updateDrink(drink.getId(), drink)).withRel("Update Drink"));
            drink.add(linkTo(methodOn(DrinkController.class).deleteDrinkById(drink.getId())).withRel("Delete Drink"));
            return drink;
        } else
            throw new ProductNotFoundException("Drink with name provided not found");
    }

    public Drink updateDrink(Long id, Drink drink) throws Exception {
        Drink existDrink = this.drinkRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Drink with ID provided not found"));
        existDrink.setName(drink.getName());
        existDrink.setDrinkType(drink.getDrinkType());
        existDrink.setPrice(drink.getPrice());

        existDrink.add(linkTo(methodOn(DrinkController.class).updateDrink(existDrink.getId(), drink)).withSelfRel());
        existDrink.add(linkTo(methodOn(DrinkController.class).getDrinkByName(existDrink.getName())).withRel("Find By Name"));
        existDrink.add(linkTo(methodOn(DrinkController.class).getDrinkByID(existDrink.getId())).withRel("Find By ID"));
        existDrink.add(linkTo(methodOn(DrinkController.class).getAllDrinks()).withRel("Find All"));
        existDrink.add(linkTo(methodOn(DrinkController.class).deleteDrinkById(existDrink.getId())).withRel("Delete Drink"));

        return this.drinkRepository.save(existDrink);
    }

    public ResponseEntity<Drink> deleteDrink(Long id) throws Exception {

        if (drinkRepository.findById(id).isPresent()) {
            this.drinkRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

