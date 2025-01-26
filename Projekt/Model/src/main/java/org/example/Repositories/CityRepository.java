package org.example.Repositories;

import org.example.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long>{

    City findByName(String name);
    void deleteByName(String name);

    Optional<City> findByname(String name);


}
