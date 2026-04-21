package com.example.TraineeAPI.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.TraineeAPI.entities.Trainee;
import com.example.TraineeAPI.services.ITraineeService;

@RestController
public class TraineeController {

    private static final Logger logger = LoggerFactory.getLogger(TraineeController.class);

    @Autowired
    private ITraineeService service;

    @GetMapping("/trainees")
    public ResponseEntity<List<Trainee>> getAllTraineesNew() {
        logger.info("GET /trainees - Request received to fetch all trainees");
        List<Trainee> list = service.getAllTrainees();
        if (list.isEmpty()) {
            logger.warn("GET /trainees - No trainees found, returning NO_CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("GET /trainees - Successfully returned {} trainees", list.size());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/trainees/{id}")
    public ResponseEntity<Trainee> getTraineeById(@PathVariable int id) {
        logger.info("GET /trainees/{} - Request received to fetch trainee by ID", id);
        Optional<Trainee> trainee = service.getTraineeById(id);
        Trainee t = null;
        if (trainee.isPresent()) {
            t = trainee.get();
            logger.info("GET /trainees/{} - Successfully returned trainee", id);
            return new ResponseEntity<>(t, HttpStatus.OK);
        } else {
            logger.warn("GET /trainees/{} - Trainee not found", id);
            return new ResponseEntity<>(t, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/trainees/byName")
    public ResponseEntity<Trainee> getByNameNew(@RequestParam("name") String name) {
        logger.info("GET /trainees/byName - Request received for name: {}", name);
        Optional<Trainee> trainee = service.getTraineeByName(name);
        if (trainee.isPresent()) {
            logger.info("GET /trainees/byName - Successfully returned trainee with name: {}", name);
            return new ResponseEntity<>(trainee.get(), HttpStatus.OK);
        } else {
            logger.warn("GET /trainees/byName - No trainee found with name: {}", name);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/trainees")
    public ResponseEntity<Trainee> addTraineeNew(@RequestBody Trainee trainee) {
        logger.info("POST /trainees - Request received to add new trainee: {}",
                trainee.getTraineeName());
        Trainee saved = service.addTrainee(trainee);
        logger.info("POST /trainees - Successfully added trainee with ID: {}",
                saved.getTraineeId());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/trainees")
    public ResponseEntity<Trainee> updateTraineeNew(@RequestBody Trainee trainee) {
        logger.info("PUT /trainees - Request received to update trainee with ID: {}",
                trainee.getTraineeId());
        Trainee updated = service.updateTrainee(trainee);
        logger.info("PUT /trainees - Successfully updated trainee with ID: {}",
                updated.getTraineeId());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/trainees/{id}")
    public ResponseEntity<String> deleteTraineeNew(@PathVariable int id) {
        logger.info("DELETE /trainees/{} - Request received to delete trainee", id);
        boolean status = service.deleteTrainee(id);
        if (status) {
            logger.info("DELETE /trainees/{} - Successfully deleted trainee", id);
            return new ResponseEntity<>("Trainee deleted successfully", HttpStatus.OK);
        } else {
            logger.warn("DELETE /trainees/{} - Trainee not found", id);
            return new ResponseEntity<>("Trainee not found", HttpStatus.NOT_FOUND);
        }
    }
}
