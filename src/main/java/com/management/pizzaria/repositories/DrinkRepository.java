package com.management.pizzaria.repositories;

import com.management.pizzaria.models.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
