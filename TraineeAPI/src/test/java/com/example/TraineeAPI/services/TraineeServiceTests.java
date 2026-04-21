package com.example.	TraineeAPI.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import com.example.TraineeAPI.entities.Trainee;
import com.example.TraineeAPI.repositories.ITraineeRepository;

@ExtendWith(MockitoExtension.class)
class TraineeServiceTests {

    @Mock  
    ITraineeRepository traineeRepo;

    @InjectMocks
    TraineeService traineeService;

    // -------------------- Helper --------------------
    private Trainee getTrainee() {
        Trainee t = new Trainee();
        t.setTraineeId(1);
        t.setTraineeName("Aman");
        t.setTraineeDomain("IT");
        t.setTraineeLocation("Delhi");
        return t;
    }

    // -------------------- getTraineeById --------------------

    @Test
    void testFindTraineeById() {

        Trainee emp = getTrainee();

        Mockito.when(traineeRepo.findById(7)).thenReturn(Optional.of(emp));

        Optional<Trainee> actualObject = traineeService.getTraineeById(7);

        assertTrue(actualObject.isPresent());
        assertEquals("Aman", actualObject.get().getTraineeName());

        Mockito.verify(traineeRepo, Mockito.times(1)).findById(7);
    }

    @Test
    void testFindTraineeByIdForNull() {

        Mockito.when(traineeRepo.findById(10)).thenReturn(Optional.empty());

        Optional<Trainee> result = traineeService.getTraineeById(10);

        assertFalse(result.isPresent());

        Mockito.verify(traineeRepo, Mockito.times(1)).findById(10);
    }

    // -------------------- getAllTrainees --------------------

    @Test
    void testGetAllTrainees() {

        List<Trainee> list = Arrays.asList(getTrainee(), getTrainee());

        Mockito.when(traineeRepo.findAll()).thenReturn(list);

        List<Trainee> result = traineeService.getAllTrainees();

        assertEquals(2, result.size());

        Mockito.verify(traineeRepo, Mockito.times(1)).findAll();
    }

    // -------------------- findByTraineeName --------------------

    @Test
    void testFindByTraineeName() {

        Trainee t = getTrainee();

        Mockito.when(traineeRepo.findByTraineeName("Aman"))
               .thenReturn(Optional.of(t));

        Optional<Trainee> result = traineeService.getTraineeByName("Aman");

        assertTrue(result.isPresent());

        Mockito.verify(traineeRepo, Mockito.times(1))
               .findByTraineeName("Aman");
    }

    // -------------------- insertTrainee --------------------

    @Test
    void testInsertTrainee() {

        Trainee trainee = getTrainee();

        Mockito.when(traineeRepo.save(trainee)).thenReturn(trainee);

        Trainee result = traineeService.addTrainee(trainee);

        assertNotNull(result);
        assertEquals("Aman", result.getTraineeName());

        Mockito.verify(traineeRepo, Mockito.times(1)).save(trainee);
    }

    // -------------------- updateTrainee --------------------

    @Test
    void testUpdateTrainee() {

        Trainee trainee = getTrainee();
        trainee.setTraineeName("Updated");

        Mockito.when(traineeRepo.save(trainee)).thenReturn(trainee);

        Trainee result = traineeService.updateTrainee(trainee);

        assertEquals("Updated", result.getTraineeName());

        Mockito.verify(traineeRepo).save(trainee);
    }

    // -------------------- deleteTrainee --------------------

    @Test
    void testDeleteTraineeSuccess() {

        Mockito.when(traineeRepo.existsById(1)).thenReturn(true);
        Mockito.doNothing().when(traineeRepo).deleteById(1);

        boolean result = traineeService.deleteTrainee(1);

        assertTrue(result);

        Mockito.verify(traineeRepo).existsById(1);
        Mockito.verify(traineeRepo).deleteById(1);
    }

    @Test
    void testDeleteTraineeFail() {

        Mockito.when(traineeRepo.existsById(1)).thenReturn(false);

        boolean result = traineeService.deleteTrainee(1);

        assertFalse(result);

        Mockito.verify(traineeRepo).existsById(1);
    }

    // -------------------- Exception Handling --------------------

    @Test
    void testNoException() {

        Mockito.when(traineeRepo.findById(7)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> traineeService.getTraineeById(7));

        Mockito.verify(traineeRepo).findById(7);
    }
}