package org.example.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class SystemInfo {
    @JsonProperty("country")
    private String country; // Kod kraju (np. "IT")

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
