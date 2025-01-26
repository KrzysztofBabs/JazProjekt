package org.example.Controller;

import org.example.Entities.*;
import org.example.Service.DataService;
import org.example.Service.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;
    private final WeatherService weatherService;

    public DataController(DataService dataService, WeatherService weatherService) {
        this.dataService = dataService;
        this.weatherService=weatherService;
    }

    @PostMapping("/city")
    public String addCity(@RequestBody City city) {
        dataService.addCity(city);
        return "City added successfully!";
    }

    @DeleteMapping("/delete")
    public String deleteCity(@RequestParam String name) {
        try {
            weatherService.deleteCityByName(name);
            return "City with name = " + name + " has been deleted.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/weather")
    public String addWeather(@RequestBody Weather weather) {
        dataService.addWeather(weather);
        return "Weather added successfully!";
    }

    @PostMapping("/clouds")
    public String addClouds(@RequestBody Clouds clouds) {
        dataService.addClouds(clouds);
        return "Clouds data added successfully!";
    }

    @PostMapping("/rain")
    public String addRain(@RequestBody Rain rain) {
        dataService.addRain(rain);
        return "Rain data added successfully!";
    }

    @PostMapping("/wind")
    public String addWind(@RequestBody Wind wind) {
        dataService.addWind(wind);
        return "Wind data added successfully!";
    }


}
