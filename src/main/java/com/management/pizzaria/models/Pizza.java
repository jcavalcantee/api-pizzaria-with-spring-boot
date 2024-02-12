package com.management.pizzaria.models;

import com.management.pizzaria.dtos.PizzaDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TB_PIZZAS")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(nullable = false, name = "Sabor")
    private String flavor;
    @Column(name = "Ingredientes")
    private String ingredients;

    public Pizza() {
    }

    public Pizza(Long id, String flavor, String ingredients) {
        this.id = id;
        this.flavor = flavor;
        this.ingredients = ingredients;
    }

    public Pizza(PizzaDTO pizzaDTO) {
        this.flavor = pizzaDTO.flavor();
        this.ingredients = pizzaDTO.ingredients();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(id, pizza.id) && Objects.equals(flavor, pizza.flavor) && Objects.equals(ingredients, pizza.ingredients);
    }

    public int hashCode() {
        return Objects.hash(id, flavor, ingredients);
    }
}
