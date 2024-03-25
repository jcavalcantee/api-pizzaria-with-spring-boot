package com.management.pizzaria.repositories;

import com.management.pizzaria.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
