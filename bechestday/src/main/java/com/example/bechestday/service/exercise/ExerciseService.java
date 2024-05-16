package com.example.bechestday.service.exercise;

import com.example.bechestday.dto.ExerciseDTO;

import java.util.List;

public interface ExerciseService {

    List<ExerciseDTO> GetAllExercises();
    ExerciseDTO GetExerciseById(Long id) throws Exception;
    ExerciseDTO PostExercise(ExerciseDTO exerciseDTO) throws Exception;
    ExerciseDTO PutExercise(ExerciseDTO exerciseDTO) throws Exception;
    void DeleteExercise(Long id) throws Exception;
}
