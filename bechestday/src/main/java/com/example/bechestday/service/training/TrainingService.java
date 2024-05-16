package com.example.bechestday.service.training;

import com.example.bechestday.dto.TrainingDTO;

import java.util.List;

public interface TrainingService {
    List<TrainingDTO> GetAllTrainings();
    TrainingDTO GetTrainingById(Long id) throws Exception;
    TrainingDTO PostTraining(TrainingDTO trainingDTO) throws Exception;
    TrainingDTO PutTraining(TrainingDTO trainingDTO) throws Exception;
    void DeleteTraining(Long id) throws Exception;
}
