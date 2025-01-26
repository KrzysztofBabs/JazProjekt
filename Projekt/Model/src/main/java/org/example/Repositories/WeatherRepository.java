package org.example.Repositories;

import org.example.Entities.City;
import org.example.Entities.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather,Long>{

    void deleteByCity(City city);

    Iterable<? extends Weather> findByCity(City city);
}
