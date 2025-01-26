package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.Entities.*;
import org.example.Repositories.*;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    private final CityRepository cityRepository;
    private final WeatherRepository weatherRepository;
    private final CloudsRepository cloudsRepository;
    private final RainRepository rainRepository;
    private final WindRepository windRepository;

    public DataService(CityRepository cityRepository,
                             WeatherRepository weatherRepository,
                             CloudsRepository cloudsRepository,
                             RainRepository rainRepository,
                             WindRepository windRepository) {
        this.cityRepository = cityRepository;
        this.weatherRepository = weatherRepository;
        this.cloudsRepository = cloudsRepository;
        this.rainRepository = rainRepository;
        this.windRepository = windRepository;
    }


    public void deleteCityByName(String name) {
        cityRepository.deleteByName(name);
    }

    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public Weather addWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    public Clouds addClouds(Clouds clouds) {
        return cloudsRepository.save(clouds);
    }

    public Rain addRain(Rain rain) {
        return rainRepository.save(rain);
    }

    public Wind addWind(Wind wind) {
        return windRepository.save(wind);
    }
}
