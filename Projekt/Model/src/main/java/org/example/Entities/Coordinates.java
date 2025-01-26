package org.example.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Coordinates{

//    @JsonProperty("lon")
    @JsonProperty("latitude")
    private Double longitude; // Długość geograficzna

//    @JsonProperty("lat")
    @JsonProperty("longitude")
    private Double latitude; // Szerokość geograficzna


    public Coordinates() {
    }


    public Coordinates(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
