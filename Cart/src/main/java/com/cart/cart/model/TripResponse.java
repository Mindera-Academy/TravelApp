package com.cart.cart.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TripResponse {
    private Integer id;
    private String city;
    private String hotel;
    private Integer numberOfPeople;
    private Double price;
}
