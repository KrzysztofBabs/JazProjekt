package org.example.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("name")
    private String name;

    @JsonProperty("sys")
//    @Embedded
    @Transient
    private SystemInfo systemInfo; // Informacje systemowe (np. kod kraju)

    private String country;

//    @JsonProperty("coord")
    @Embedded
    private Coordinates coordinates; // Współrzędne geograficzne

    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Weather> weatherRecords;

    public List<Weather> getWeatherRecords() {
        return weatherRecords;
    }

    public void setWeatherRecords(List<Weather> weatherRecords) {
        this.weatherRecords = weatherRecords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country=country;
    }


}
