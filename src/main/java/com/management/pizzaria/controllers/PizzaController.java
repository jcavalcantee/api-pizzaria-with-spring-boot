package com.management.pizzaria.controllers;

import com.management.pizzaria.dtos.PizzaDTO;
import com.management.pizzaria.models.Customer;
import com.management.pizzaria.models.Pizza;
import com.management.pizzaria.services.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("/api/v1/pizzas")
@Tag(name = "Pizza", description = "Endpoints for Managing Pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @PostMapping
    @Operation(summary = "Adds a new Pizza", description = "Adds a new Pizza by passing in a JSON representation of pizza",
            tags = {"Pizza"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Pizza.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Pizza> createPizza(@RequestBody PizzaDTO pizzaDTO) throws Exception {
        Pizza newPizza = this.pizzaService.createPizza(pizzaDTO);
        return new ResponseEntity<>(newPizza, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Finds all Pizzas", description = "Finds all Pizzas",
            tags = {"Pizza"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Customer.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<Pizza>> getAllPizzas() {
        var allPizzas = this.pizzaService.getAllPizzas();
        return new ResponseEntity<>(allPizzas, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Finds a Pizza", description = "Finds all Pizza",
            tags = {"Pizza"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Pizza.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Pizza> getPizzaById(@PathVariable (value = "id") Long id) throws Exception {
        var pizza = this.pizzaService.getPizzaById(id);
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @GetMapping("/flavor/{flavor}")
    @Operation(summary = "Finds a Pizza by Flavor", description = "Finds a Pizza by Flavor",
            tags = {"Pizza"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Pizza.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Pizza> getPizzaByFlavor(@PathVariable (value = "flavor") String flavor) throws Exception {
        var pizza = pizzaService.getPizzaByFlavor(flavor);
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Updates a Pizza", description = "Updates a Pizza by passing in a JSON representation of pizza",
            tags = {"Pizza"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Pizza.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Pizza> updatePizza(@PathVariable (value = "id") Long id, @RequestBody Pizza pizza) throws Exception {
        var pizzaUpdate = this.pizzaService.updatePizza(id, pizza);
        return new ResponseEntity<>(pizzaUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a Pizza", description = "Delete a Pizza",
            tags = {"Pizza"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Pizza> deletePizza(@PathVariable (value = "id") Long id) {
        return this.pizzaService.deletePizza(id);
    }
}
