package com.management.pizzaria.models;

import com.management.pizzaria.dtos.DrinkDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TB_DRINKS")
public class Drink extends Product{


    @Column(nullable = false, name = "Nome")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drink() {
    }

    public Drink(String name) {
        this.name = name;
    }

    public Drink (DrinkDTO drinkDTO) {
        this.name = drinkDTO.name();
        this.price = drinkDTO.price();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Drink drink = (Drink) o;
        return Objects.equals(name, drink.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
