package com.management.pizzaria.services;

import com.management.pizzaria.dtos.DrinkDTO;
import com.management.pizzaria.exceptions.ProductNotFoundException;
import com.management.pizzaria.models.Drink;
import com.management.pizzaria.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinkService extends ProductService{

    @Autowired
    private DrinkRepository drinkRepository;

    public void save(Drink drink) {
        this.drinkRepository.save(drink);
    }

    public Drink createDrink(DrinkDTO drinkDTO) {
        Drink newDrink = new Drink(drinkDTO);
        this.save(newDrink);
        return newDrink;
    }

    public List<Drink> getAllDrinks() {
        return this.drinkRepository.findAll();
    }

    public Drink getDrinkById(Long id) throws Exception {
        return this.drinkRepository.findById(id).orElseThrow(() -> new Exception("Drink with ID provided not found"));
    }

    public Drink getDrinkByName(String name) throws ProductNotFoundException {
        Optional<Drink> drinkOptional = Optional.ofNullable(this.drinkRepository.findByName(name));
        if (drinkOptional.isPresent()) {
            return drinkOptional.get();
        } else {
            throw new ProductNotFoundException("Drink with name provided not found");
        }
    }

    public Drink updateDrink(Long id, Drink drink) throws Exception {
        Drink existDrink = this.drinkRepository.findById(id).orElseThrow(() -> new Exception("Drink with ID provided not found"));
        existDrink.setName(drink.getName());
        existDrink.setDrinkType(drink.getDrinkType());
        existDrink.setPrice(drink.getPrice());
        return this.drinkRepository.save(existDrink);
    }

    public ResponseEntity<Drink> deleteDrink(Long id) {

        if (drinkRepository.findById(id).isPresent()) {
            this.drinkRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

