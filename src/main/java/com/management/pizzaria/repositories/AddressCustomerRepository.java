package com.management.pizzaria.repositories;

import com.management.pizzaria.models.AddressCustomer;
import com.management.pizzaria.models.AddressCustomerKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressCustomerRepository extends JpaRepository<AddressCustomer, AddressCustomerKey> {
}
