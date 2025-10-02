package com.gymmate.gym.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    
    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        if (street != null && !street.trim().isEmpty()) {
            sb.append(street.trim());
        }
        if (city != null && !city.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(city.trim());
        }
        if (state != null && !state.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(state.trim());
        }
        if (zipCode != null && !zipCode.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(zipCode.trim());
        }
        if (country != null && !country.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(country.trim());
        }
        return sb.toString();
    }
}