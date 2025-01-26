package org.example.Repositories;

import org.example.Entities.Clouds;
import org.example.Entities.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloudsRepository extends JpaRepository<Clouds,Long>{
    void deleteByWeather(Weather weather);
}
