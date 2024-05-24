package com.example.bechestday.service.exercise;

import com.example.bechestday.dto.ExerciseDTO;

import java.util.List;

public interface ExerciseService {

    List<ExerciseDTO> getAllExercises();
    ExerciseDTO getExerciseById(Long id) throws Exception;
    ExerciseDTO postExercise(ExerciseDTO exerciseDTO) throws Exception;
    ExerciseDTO putExercise(ExerciseDTO exerciseDTO) throws Exception;
    void deleteExercise(Long id) throws Exception;
}
