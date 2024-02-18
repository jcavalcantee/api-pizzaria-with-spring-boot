package com.management.pizzaria.controllers;

import com.management.pizzaria.dtos.DrinkDTO;
import com.management.pizzaria.models.Drink;
import com.management.pizzaria.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drinks")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @PostMapping
    public ResponseEntity<Drink> createDrink (@RequestBody DrinkDTO drinkDTO) {
        Drink newDrink = this.drinkService.createDrink(drinkDTO);
        return new ResponseEntity<>(newDrink, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Drink>> getAllDrinks() {
        var allDrinks = this.drinkService.getAllDrinks();
        return new ResponseEntity<>(allDrinks, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Drink> getDrinkByID(@PathVariable (value = "id") Long id) throws Exception{
        var drink = this.drinkService.getDrinkById(id);
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Drink> getDrinkByName(@PathVariable (value = "name") String name) throws Exception {
        var drink = this.drinkService.getDrinkByName(name);
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Drink> updateDrink(@PathVariable (value = "id") Long id, @RequestBody Drink drink) throws Exception {
        var drinkUpdate = this.drinkService.updateDrink(id, drink);
        return new ResponseEntity<>(drinkUpdate, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Drink> deleteDrinkById(@PathVariable (value = "id") Long id) throws Exception {
        return this.drinkService.deleteDrink(id);
    }
}
