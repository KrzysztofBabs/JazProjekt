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


    //PUT
    @PutMapping("/city")
    public String updateCity(@RequestBody City city) {
        dataService.updateCity(city);
        return "City updated successfully!";
    }

    @PutMapping("/weather")
    public String updateWeather(@RequestBody Weather weather) {
        dataService.updateWeather(weather);
        return "Weather updated successfully!";
    }

    @PutMapping("/clouds")
    public String updateClouds(@RequestBody Clouds clouds) {
        dataService.updateClouds(clouds);
        return "Clouds data updated successfully!";
    }

    @PutMapping("/rain")
    public String updateRain(@RequestBody Rain rain) {
        dataService.updateRain(rain);
        return "Rain data updated successfully!";
    }

    @PutMapping("/wind")
    public String updateWind(@RequestBody Wind wind) {
        dataService.updateWind(wind);
        return "Wind data updated successfully!";
    }

      //DELETE

    @DeleteMapping("/deleteCity")
    public String deleteCity(@RequestParam String name) {
        try {
            weatherService.deleteCityByName(name);
            return "City with name = " + name + " has been deleted.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
//    @DeleteMapping("/deleteCity")
//    public String deleteCity(@RequestParam Long id) {
//        try {
//            dataService.deleteCityById(id);
//            return "City with id= " + id + " has been deleted.";
//        } catch (IllegalArgumentException e) {
//            return e.getMessage();
//        }
//    }

    @DeleteMapping("/deleteWeather")
    public String deleteWeather(@RequestParam Long id) {
        try {
            dataService.deleteWeatherById(id);
            return "Weather with id= " +id + " has been deleted.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/deleteClouds")
    public String deleteClouds(@RequestParam Long id) {
        try {
            dataService.deleteCloudsById(id);
            return "Clouds data with ID = " + id + " has been deleted.";
        }catch(IllegalArgumentException e){
             return  e.getMessage();
        }
    }

    @DeleteMapping("/deleteRain")
    public String deleteRain(@RequestParam Long id) {
        dataService.deleteRainById(id);
        return "Rain data with ID = " + id + " has been deleted.";
    }

    @DeleteMapping("/deleteWind")
    public String deleteWind(@RequestParam Long id) {
        dataService.deleteWindById(id);
        return "Wind data with ID = " + id + " has been deleted.";
    }




}
