package org.example.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.Entities.*;
import org.example.Repositories.*;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class WeatherService {
    private final CityRepository cityRepository;
    private final WeatherRepository weatherRepository;
    private final CloudsRepository cloudsRepository;
    private final RainRepository rainRepository;
    private final WindRepository windRepository;

    public WeatherService(CityRepository cityRepository,
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

    public void fetchAndSaveWeather(String cityName) throws Exception {

        String apiKey = "a72c130d5554dec7e93d3ea71acb5e62";
        String apiUrl = String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",
                cityName, apiKey
        );

        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("GET");

        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder jsonResponse = new StringBuilder();
        while (scanner.hasNext()) {
            jsonResponse.append(scanner.nextLine());
        }
        scanner.close();

        // 2. Parsowanie JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonResponse.toString());


        String name = root.get("name").asText();
        String country = root.get("sys").get("country").asText();
        double latitude = root.get("coord").get("lat").asDouble();
        double longitude = root.get("coord").get("lon").asDouble();

        City city = cityRepository.findByName(name);
        if (city == null) {
            city = new City();
            city.setName(name);
            city.setCountry(country);
            city.setCoordinates(new Coordinates(latitude, longitude));
            cityRepository.save(city);
        }

        // 5. Pobranie danych pogodowych
        Weather weather = new Weather();
        weather.setCity(city);
        weather.setTemperature(root.get("main").get("temp").asDouble());
        weather.setFeelsLike(root.get("main").get("feels_like").asDouble());
        weather.setHumidity(root.get("main").get("humidity").asInt());
        weather.setPressure(root.get("main").get("pressure").asInt());
        weather.setMain(root.get("weather").get(0).get("main").asText());
        weather.setDescription(root.get("weather").get(0).get("description").asText());
        weatherRepository.save(weather);

        // 6. Pobranie danych zachmurzenia
        if (root.has("clouds")) {
            Clouds clouds = new Clouds();
            clouds.setCloudiness(root.get("clouds").get("all").asInt());
            clouds.setWeather(weather);
            cloudsRepository.save(clouds);
        }

        // 7. Pobranie danych deszczu
        if (root.has("rain") && root.get("rain").has("1h")) {
            Rain rain = new Rain();
            rain.setRain1h(root.get("rain").get("1h").asDouble());
            rain.setWeather(weather);
            rainRepository.save(rain);
        }

        // 8. Pobranie danych wiatru
        if (root.has("wind")) {
            Wind wind = new Wind();
            wind.setSpeed(root.get("wind").get("speed").asDouble());
            wind.setDirection(root.get("wind").get("deg").asInt());
            if (root.get("wind").has("gust")) {
                wind.setGust(root.get("wind").get("gust").asDouble());
            }
            wind.setWeather(weather);
            windRepository.save(wind);
        }
    }


    @Transactional
    public void deleteCityByName(String name) {
        City city = cityRepository.findByname(name)
                .orElseThrow(() -> new IllegalArgumentException("City with name = " + name + " does not exist."));
        weatherRepository.deleteByCity(city);

        cityRepository.delete(city);
    }
}
