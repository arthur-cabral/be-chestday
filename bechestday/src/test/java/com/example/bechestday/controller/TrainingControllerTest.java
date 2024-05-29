package com.example.bechestday.controller;

import com.example.bechestday.dto.TrainingDTO;
import com.example.bechestday.exception.NotFoundException;
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
class TrainingControllerTest {

    TrainingController trainingController;

    TrainingService trainingService;

    @BeforeEach
    void initialize(){
        trainingService = mock(TrainingService.class);
        trainingController = new TrainingController(trainingService);
    }

    @Test
    void getAllTrainings_shouldReturnAllTrainingRecords() {
        List<TrainingDTO> trainings = List.of(new TrainingDTO());
        when(trainingService.getAllTrainings()).thenReturn(trainings);

        ResponseEntity<List<TrainingDTO>> result = trainingController.getAllTrainings();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(trainings, result.getBody());
    }

    @Test
    void getTrainingById_whenFind_shouldReturnOk() throws Exception {
        when(trainingService.getTrainingById(1L)).thenReturn(new TrainingDTO());

        ResponseEntity<TrainingDTO> result = trainingController.getTrainingById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getTrainingById_whenNotFound_shouldReturnNotFound() throws Exception {
        when(trainingService.getTrainingById(1L)).thenThrow(new Exception());

        ResponseEntity<TrainingDTO> result = trainingController.getTrainingById(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void postTraining_shouldReturnOk() throws Exception {
        TrainingDTO trainingDTO = new TrainingDTO();
        when(trainingService.postTraining(trainingDTO)).thenReturn(trainingDTO);

        ResponseEntity<TrainingDTO> result = (ResponseEntity<TrainingDTO>) trainingController.postTraining(trainingDTO);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(trainingDTO, result.getBody());
    }

    @Test
    void putTraining_whenFind_shouldReturnOk() throws Exception {
        when(trainingService.putTraining(new TrainingDTO())).thenReturn(new TrainingDTO());

        ResponseEntity<TrainingDTO> result = trainingController.putTraining(1L, new TrainingDTO());

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void putTraining_whenNotFound_shouldReturnNotFound() throws Exception {
        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setTrainingId(1L);

        when(trainingService.putTraining(trainingDTO)).thenThrow(new NotFoundException());

        ResponseEntity<TrainingDTO> result = trainingController.putTraining(1L, trainingDTO);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void putTraining_whenOccursGenericError_shouldReturnBadRequest() throws Exception {
        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setTrainingId(1L);

        when(trainingService.putTraining(trainingDTO)).thenThrow(new Exception());

        ResponseEntity<TrainingDTO> result = trainingController.putTraining(1L, trainingDTO);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void deleteTraining_whenFind_shouldReturnNoContent() {
        ResponseEntity<TrainingDTO> result = trainingController.deleteTraining(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void deleteTraining_whenNotFound_shouldReturnNotFound() throws Exception {
        doThrow(new NotFoundException()).when(trainingService).deleteTraining(1L);

        ResponseEntity<TrainingDTO> result = trainingController.deleteTraining(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}