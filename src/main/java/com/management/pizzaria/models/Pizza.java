package com.management.pizzaria.models;

import com.management.pizzaria.dtos.PizzaDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TB_PIZZAS")
public class Pizza extends Product{

    @Column(nullable = false, name = "Sabor")
    private String flavor;
    @Column(name = "Ingredientes")
    private String ingredients;

    public Pizza() {
    }

    public Pizza(String flavor, String ingredients) {

        this.flavor = flavor;
        this.ingredients = ingredients;
    }

    public Pizza(PizzaDTO pizzaDTO) {
        this.flavor = pizzaDTO.flavor();
        this.ingredients = pizzaDTO.ingredients();
        this.price = pizzaDTO.price();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(flavor, pizza.flavor) && Objects.equals(ingredients, pizza.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), flavor, ingredients);
    }
}
