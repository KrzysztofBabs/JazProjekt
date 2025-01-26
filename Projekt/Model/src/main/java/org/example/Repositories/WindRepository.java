package org.example.Repositories;

import org.example.Entities.Weather;
import org.example.Entities.Wind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindRepository extends JpaRepository<Wind,Long>{
    void deleteByWeather(Weather weather);
}
