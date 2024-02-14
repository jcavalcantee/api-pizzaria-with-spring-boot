package com.management.pizzaria.models;

import com.management.pizzaria.dtos.DrinkDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TB_DRINKS")
public class Drink extends Product{


    @Column(nullable = false, name = "Nome")
    private String name;

    @Column(nullable = false, name = "Tipo")
    private String drinkType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public Drink() {
    }

    public Drink(String name, String drinkType) {
        this.name = name;
        this.drinkType = drinkType;
    }

    public Drink (DrinkDTO drinkDTO) {
        this.name = drinkDTO.name();
        this.drinkType = drinkDTO.drinkType();
        this.price = drinkDTO.price();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Drink drink = (Drink) o;
        return Objects.equals(name, drink.name) && Objects.equals(drinkType, drink.drinkType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, drinkType);
    }
}
