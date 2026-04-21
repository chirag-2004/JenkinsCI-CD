package com.example.TraineeAPI.services;

import java.util.List;
import java.util.Optional;

import com.example.TraineeAPI.entities.Trainee;

public interface ITraineeService {
	List<Trainee> getAllTrainees();

    Optional<Trainee> getTraineeById(int id);
    
    Optional<Trainee> getTraineeByName(String name); 

    Trainee addTrainee(Trainee trainee);

    Trainee updateTrainee(Trainee trainee);

    boolean deleteTrainee(int id);
}
