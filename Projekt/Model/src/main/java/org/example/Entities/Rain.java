package org.example.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Rain{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "weather_id")
    private Weather weather; // Powiązanie z pogodą

    @JsonProperty("1h")
    private Double rain1h; // Opady w ciągu ostatniej godziny (w mm)

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

    public Double getRain1h() {
        return rain1h;
    }

    public void setRain1h(Double rain1h) {
        this.rain1h = rain1h;
    }
}
