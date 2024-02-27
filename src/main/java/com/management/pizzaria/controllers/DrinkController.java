package com.management.pizzaria.controllers;

import com.management.pizzaria.dtos.DrinkDTO;
import com.management.pizzaria.exceptions.ProductNotFoundException;
import com.management.pizzaria.models.Customer;
import com.management.pizzaria.models.Drink;
import com.management.pizzaria.services.DrinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drinks")
@Tag(name = "Drink", description = "Endpoints for Managing Drinks")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @PostMapping
    @Operation(summary = "Adds a new Drink", description = "Adds a new Drink by passing in a JSON representation of drink",
            tags = {"Drink"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Drink.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Drink> createDrink (@RequestBody DrinkDTO drinkDTO) throws Exception {
        Drink newDrink = this.drinkService.createDrink(drinkDTO);
        return new ResponseEntity<>(newDrink, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Finds all Drinks", description = "Finds all Drinks",
            tags = {"Drink"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Drink.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<Drink>> getAllDrinks() {
        var allDrinks = this.drinkService.getAllDrinks();
        return new ResponseEntity<>(allDrinks, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Finds a Drink", description = "Finds all Drink",
            tags = {"Drink"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Drink.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Drink> getDrinkByID(@PathVariable (value = "id") Long id) throws Exception {
        var drink = this.drinkService.getDrinkById(id);
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Finds a Drink by Name", description = "Finds a Drink by Name",
            tags = {"Drink"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Drink.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Drink> getDrinkByName(@PathVariable (value = "name") String name) throws Exception {
        var drink = this.drinkService.getDrinkByName(name);
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Updates a Drink", description = "Updates a Drink by passing in a JSON representation of drink",
            tags = {"Drink"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Drink.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Drink> updateDrink(@PathVariable (value = "id") Long id, @RequestBody Drink drink) throws Exception {
        var drinkUpdate = this.drinkService.updateDrink(id, drink);
        return new ResponseEntity<>(drinkUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a Drink", description = "Delete a Drink",
            tags = {"Drink"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Drink> deleteDrinkById(@PathVariable (value = "id") Long id) throws Exception {
        return this.drinkService.deleteDrink(id);
    }
}
