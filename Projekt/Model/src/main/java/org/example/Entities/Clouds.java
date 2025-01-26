package org.example.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Clouds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "weather_id")
    private Weather weather; // Powiązanie z pogodą

    @JsonProperty("cloudiness")
    private Integer cloudiness; // Zachmurzenie w % (np. 83)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Integer getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(Integer cloudiness) {
        this.cloudiness = cloudiness;
    }
}
