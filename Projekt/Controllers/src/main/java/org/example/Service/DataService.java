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

    @Transactional
    public void updateCity(City city) {
        City existingCity = cityRepository.findById(city.getId())
                .orElseThrow(() -> new IllegalArgumentException("City with ID = " + city.getId() + " does not exist."));


        existingCity.setName(city.getName());
        existingCity.setCountry(city.getCountry());
        existingCity.setCoordinates(city.getCoordinates());

        cityRepository.save(existingCity);
    }

    @Transactional
    public void updateWeather(Weather weather) {
        Weather existingWeather = weatherRepository.findById(weather.getId())
                .orElseThrow(()->new IllegalArgumentException("Weather with ID = " + weather.getId() + " does not exist."));

        // Pobranie powiązanego miasta na podstawie city_id z JSON-a
        City city = cityRepository.findById(weather.getCity().getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "City with ID = " + weather.getCity().getId() + " does not exist."));

        // Aktualizacja pól w istniejącym obiekcie Weather
        existingWeather.setCity(city);
        existingWeather.setFeelsLike(weather.getFeelsLike());
        existingWeather.setHumidity(weather.getHumidity());
        existingWeather.setPressure(weather.getPressure());
        existingWeather.setTemperature(weather.getTemperature());
        existingWeather.setDescription(weather.getDescription());
        existingWeather.setMain(weather.getMain());

        weatherRepository.save(existingWeather);

    }

    @Transactional
    public void updateClouds(Clouds clouds) {
        Clouds existingClouds = cloudsRepository.findById(clouds.getId())
                        .orElseThrow(()->new IllegalArgumentException("Cloud with ID = " + clouds.getId() + " does not exist."));

        Weather weather = weatherRepository.findById(clouds.getWeather().getId())
                        .orElseThrow(()-> new IllegalArgumentException("Weather with ID = " + clouds.getWeather().getId() + " does not exist."));

        existingClouds.setWeather(weather);
        existingClouds.setCloudiness(clouds.getCloudiness());


        cloudsRepository.save(existingClouds);
    }


    @Transactional
    public void updateRain(Rain rain) {

        Rain existingRain = rainRepository.findById(rain.getId())
                .orElseThrow(()->new IllegalArgumentException("Rain with ID = " + rain.getId() + " does not exist."));

        Weather weather = weatherRepository.findById(rain.getWeather().getId())
                .orElseThrow(()-> new IllegalArgumentException("Weather with ID = " + rain.getWeather().getId() + " does not exist."));

        existingRain.setWeather(weather);
        existingRain.setRain1h(rain.getRain1h());


        rainRepository.save(existingRain);
    }

    @Transactional
    public void updateWind(Wind wind) {
        Wind existingWind = windRepository.findById(wind.getId())
                .orElseThrow(()->new IllegalArgumentException("Wind with ID = " + wind.getId() + " does not exist."));

        Weather weather = weatherRepository.findById(wind.getWeather().getId())
                .orElseThrow(()-> new IllegalArgumentException("Weather with ID = " + wind.getWeather().getId() + " does not exist."));

        existingWind.setWeather(weather);

        existingWind.setDirection(wind.getDirection());
        existingWind.setGust(existingWind.getGust());
        existingWind.setSpeed(existingWind.getSpeed());

        windRepository.save(existingWind);


    }

    public void deleteCityById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("City with id = " + id + " does not exist."));

        cityRepository.delete(city);
    }

    public void deleteWeatherById(Long id) {

        Weather weather = weatherRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Weather  with id = " +id + " does not exist."));

        weatherRepository.delete(weather);
    }

    public void deleteCloudsById(Long id) {
        Clouds clouds = cloudsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Clouds  with id = " +id + " does not exist."));

        cloudsRepository.delete(clouds);
    }


    public void deleteRainById(Long id) {

        Rain rain = rainRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Rain with id = " +id + " does not exist."));
        rainRepository.delete(rain);
    }


    public void deleteWindById(Long id) {
        Wind wind = windRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Wind with id = " +id + " does not exist."));

        windRepository.delete(wind);

    }
}
