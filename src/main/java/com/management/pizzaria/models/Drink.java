package com.management.pizzaria.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TB_DRINKS")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(nullable = false, name = "Nome")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return Objects.equals(id, drink.id) && Objects.equals(name, drink.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
