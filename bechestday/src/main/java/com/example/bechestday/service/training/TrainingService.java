package com.example.bechestday.service.training;

import com.example.bechestday.dto.TrainingDTO;

import java.util.List;

public interface TrainingService {
    List<TrainingDTO> getAllTrainings();
    TrainingDTO getTrainingById(Long id) throws Exception;
    TrainingDTO postTraining(TrainingDTO trainingDTO) throws Exception;
    TrainingDTO putTraining(TrainingDTO trainingDTO) throws Exception;
    void deleteTraining(Long id) throws Exception;
}
