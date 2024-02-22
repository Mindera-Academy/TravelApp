package com.Trip.controller;

import com.Trip.exceptions.InvalidDateException;
import com.Trip.exceptions.InvalidNumberOfPeopleException;
import com.Trip.exceptions.TripNotFoundException;
import com.Trip.model.Trip;
import com.Trip.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable Integer id) {
        Trip trip = tripService.getOne(id);
        return ResponseEntity.ok(trip);
    }

    @GetMapping()
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.getAll();
        return ResponseEntity.ok(trips);
    }

    @PostMapping()
    public ResponseEntity<Trip> addTrip(@RequestBody Trip trip) {
        Trip added = tripService.addOne(trip);
        return ResponseEntity.status(HttpStatus.CREATED).body(added);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTrip(@PathVariable Integer id, @RequestBody Trip trip) {
        tripService.update(id, trip);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Integer id) {
        tripService.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}/numberOfPeople")
    public ResponseEntity<Void> updateNumberOfPeople(@PathVariable Integer id, @RequestParam Integer numberOfPeople) {
        tripService.updateNumberOfPeople(id, numberOfPeople);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ExceptionHandler(TripNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleTripNotFoundException(TripNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidDateException(InvalidDateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidNumberOfPeopleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidNumberOfPeopleException(InvalidNumberOfPeopleException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}