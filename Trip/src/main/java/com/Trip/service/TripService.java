package com.Trip.service;


import com.Trip.exceptions.InvalidDateException;
import com.Trip.exceptions.InvalidNumberOfPeopleException;
import com.Trip.exceptions.TripNotFoundException;
import com.Trip.model.Trip;
import com.Trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip getOne(Integer id) {
        Optional<Trip> trip = tripRepository.findById(id);
        return trip.orElseThrow(() -> new TripNotFoundException("Trip " + id + " not found"));
    }

    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    public Trip addOne(Trip trip) {
        validateTripDates(trip);
        calculatePrice(trip);
        return tripRepository.save(trip);
    }

    public void update(Integer id, Trip trip) {
        tripRepository.findById(id).orElseThrow(() -> new TripNotFoundException("Trip " + id + " not found"));
        trip.setId(id);
        validateTripDates(trip);
        calculatePrice(trip);
        tripRepository.save(trip);
    }

    public void remove(Integer id) {
        Optional<Trip> trip = tripRepository.findById(id);
        trip.orElseThrow(() -> new TripNotFoundException("Trip " + id + " not found"));
        tripRepository.delete(trip.get());
    }

    public void updateNumberOfPeople(Integer id, Integer numberOfPeople) {
        Trip trip = getOne(id);
        trip.setNumberOfPeople(numberOfPeople);
        calculatePrice(trip);
        tripRepository.save(trip);
    }

    private void calculatePrice(Trip trip) {
        LocalDate start = LocalDate.parse(trip.getStartDate());
        LocalDate end = LocalDate.parse(trip.getEndDate());
        int numberOfNights = (int) ChronoUnit.DAYS.between(start, end);
        trip.setPrice(trip.getNumberOfPeople() * numberOfNights * 100.0);
    }

    private void validateTripDates(Trip trip) {
        LocalDate start = LocalDate.parse(trip.getStartDate());
        LocalDate end = LocalDate.parse(trip.getEndDate());
        if (start.isAfter(end)) {
            throw new InvalidDateException("Start date cannot be after end date");
        }
    }

    private void validateNumberOfPeople(Trip trip) {
        if (trip.getNumberOfPeople() <= 0) {
            throw new InvalidNumberOfPeopleException("Number of people must be greater than 0");
        }
    }
}