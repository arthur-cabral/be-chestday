package com.example.bechestday.controller;

import com.example.bechestday.dto.ExerciseDTO;
import com.example.bechestday.dto.TrainingDTO;
import com.example.bechestday.exception.NotFoundException;
import com.example.bechestday.service.exercise.ExerciseService;
import com.example.bechestday.service.training.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ExerciseControllerTest {
    ExerciseController exerciseController;

    ExerciseService exerciseService;

    @BeforeEach
    void initialize(){
        exerciseService = mock(ExerciseService.class);
        exerciseController = new ExerciseController(exerciseService);
    }

    @Test
    void getAllExercises_shouldReturnAllExerciseRecords(){
        List<ExerciseDTO> exercises = List.of(new ExerciseDTO());
        when(exerciseService.getAllExercises()).thenReturn(exercises);

        ResponseEntity<List<ExerciseDTO>> result = exerciseController.getAllExercises();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(exercises, result.getBody());
    }

    @Test
    void getExerciseById_whenFind_shouldReturnOk() throws Exception {
        when(exerciseService.getExerciseById(1L)).thenReturn(new ExerciseDTO());

        ResponseEntity<ExerciseDTO> result = exerciseController.getExerciseById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getExerciseById_whenNotFound_shouldReturnNotFound() throws Exception {
        when(exerciseService.getExerciseById(1L)).thenThrow(new NotFoundException());

        ResponseEntity<ExerciseDTO> result = exerciseController.getExerciseById(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void postExercise_shouldReturnOk() throws Exception {
        when(exerciseService.postExercise(new ExerciseDTO())).thenReturn(new ExerciseDTO());

        ResponseEntity<ExerciseDTO> result = exerciseController.postExercise(new ExerciseDTO());

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void putExercise_whenFind_shouldReturnOk() throws Exception {
        when(exerciseService.putExercise(new ExerciseDTO())).thenReturn(new ExerciseDTO());

        ResponseEntity<ExerciseDTO> result = exerciseController.putExercise(1L, new ExerciseDTO());

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void putExercise_whenNotFound_shouldReturnNotFound() throws Exception {
        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseDTO.setExerciseId(1L);

        when(exerciseService.putExercise(exerciseDTO)).thenThrow(new NotFoundException());

        ResponseEntity<ExerciseDTO> result = exerciseController.putExercise(1L, exerciseDTO);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void putExercise_whenOccursGenericError_shouldReturnBadRequest() throws Exception {
        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseDTO.setExerciseId(1L);

        when(exerciseService.putExercise(exerciseDTO)).thenThrow(new Exception());

        ResponseEntity<ExerciseDTO> result = exerciseController.putExercise(1L, exerciseDTO);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void deleteExercise_whenFind_shouldReturnNoContent() {
        ResponseEntity<ExerciseDTO> result = exerciseController.deleteExercise(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void deleteExercise_whenNotFound_shouldReturnNotFound() throws Exception {
        doThrow(new NotFoundException()).when(exerciseService).deleteExercise(1L);

        ResponseEntity<ExerciseDTO> result = exerciseController.deleteExercise(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

}