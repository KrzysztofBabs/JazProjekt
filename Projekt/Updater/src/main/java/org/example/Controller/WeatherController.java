package org.example.Controller;
import org.example.Service.WeatherService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // Obsługa wielu miast na raz
    @PostMapping("/fetch-weather")
    public String fetchWeatherForCities(@RequestBody List<String> cityNames) {
        StringBuilder response = new StringBuilder();
        for (String cityName : cityNames) {
            try {
                weatherService.fetchAndSaveWeather(cityName); // Wywołanie serwisu dla każdego miasta
                response.append("Weather data for ").append(cityName).append(" fetched and saved successfully!\n");
            } catch (Exception e) {
                response.append("Error fetching weather data for ").append(cityName).append(": ").append(e.getMessage()).append("\n");
            }
        }
        return response.toString();
    }

    @GetMapping("/fetch-weather")
    public String fetchWeather(@RequestParam String cityName) {
        try {
            weatherService.fetchAndSaveWeather(cityName);
            return "Weather data for " + cityName + " fetched and saved successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
