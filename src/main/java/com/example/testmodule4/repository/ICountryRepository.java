package com.example.testmodule4.repository;

import com.example.testmodule4.model.City;
import com.example.testmodule4.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {
    Iterable<Country> findAllByNameContainingIgnoreCase(String name);
}
