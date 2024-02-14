package com.management.pizzaria.services;

import com.management.pizzaria.dtos.DrinkDTO;
import com.management.pizzaria.models.Drink;
import com.management.pizzaria.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Drink updateDrink(Long id, Drink drink) throws Exception {
        Drink existDrink = this.drinkRepository.findById(id).orElseThrow(() -> new Exception("Drink with ID provided not found"));
        existDrink.setName(drink.getName());
        existDrink.setDrinkType(drink.getDrinkType());
        existDrink.setPrice(drink.getPrice());
        return this.drinkRepository.save(existDrink);
    }

    public void deleteDrink(Long id) throws EmptyResultDataAccessException{
        try {
            this.drinkRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
