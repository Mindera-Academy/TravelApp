package com.cart.cart.service;

import com.cart.cart.model.TripResponse;
import com.cart.cart.properties.AppProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class TripService {
    private final RestTemplate restTemplate;
    private final AppProperty property;

    public TripResponse getTripById(Integer tripId) {
        var trip = property.getTrip();
        URI uri = URI.create("http://" + trip.getHost() + ":" + trip.getPort() + trip.getBaseUrl() + "/" + tripId);
        return restTemplate.getForObject(uri, TripResponse.class);
    }
}
