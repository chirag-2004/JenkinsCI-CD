package com.example.TraineeAPI.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TraineeAPI.entities.Trainee;
import com.example.TraineeAPI.repositories.ITraineeRepository;

@Service
public class TraineeService implements ITraineeService {

    private static final Logger logger = LoggerFactory.getLogger(TraineeService.class);

    @Autowired
    private ITraineeRepository repo;

    @Override
    public List<Trainee> getAllTrainees() {
        logger.info("Service - Fetching all trainees from database");
        try {
            List<Trainee> trainees = repo.findAll();
            logger.info("Service - Successfully retrieved {} trainees", trainees.size());
            return trainees;
        } catch (Exception e) {
            logger.error("Service - Error fetching all trainees: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<Trainee> getTraineeById(int id) {
        logger.info("Service - Fetching trainee by ID: {}", id);
        try {
            Optional<Trainee> trainee = repo.findById(id);
            if (trainee.isPresent()) {
                logger.info("Service - Successfully retrieved trainee with ID: {}", id);
            } else {
                logger.warn("Service - Trainee with ID {} not found", id);
            }
            return trainee;
        } catch (Exception e) {
            logger.error("Service - Error fetching trainee by ID {}: {}", id, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Trainee> getTraineeByName(String name) {
        logger.info("Service - Fetching trainee by name: {}", name);
        try {
            Optional<Trainee> trainee = repo.findByTraineeName(name);
            if (trainee.isPresent()) {
                logger.info("Service - Successfully retrieved trainee with name: {}", name);
            } else {
                logger.warn("Service - No trainee found with name: {}", name);
            }
            return trainee;
        } catch (Exception e) {
            logger.error("Service - Error fetching trainee by name {}: {}", name, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Trainee addTrainee(Trainee trainee) {
        logger.info("Service - Adding new trainee: {}", trainee.getTraineeName());
        try {
            Trainee saved = repo.save(trainee);
            logger.info("Service - Successfully added trainee with ID: {} and name: {}",
                    saved.getTraineeId(), saved.getTraineeName());
            return saved;
        } catch (Exception e) {
            logger.error("Service - Error adding trainee {}: {}",
                    trainee.getTraineeName(), e.getMessage());
            return null;
        }
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        logger.info("Service - Updating trainee with ID: {}", trainee.getTraineeId());
        try {
            Trainee updated = repo.save(trainee);
            logger.info("Service - Successfully updated trainee with ID: {}",
                    updated.getTraineeId());
            return updated;
        } catch (Exception e) {
            logger.error("Service - Error updating trainee with ID {}: {}",
                    trainee.getTraineeId(), e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteTrainee(int id) {
        logger.info("Service - Deleting trainee with ID: {}", id);
        try {
            if (repo.existsById(id)) {
                repo.deleteById(id);
                logger.info("Service - Successfully deleted trainee with ID: {}", id);
                return true;
            } else {
                logger.warn("Service - Cannot delete - trainee with ID {} not found", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("Service - Error deleting trainee with ID {}: {}", id, e.getMessage());
            return false;
        }
    }
}
