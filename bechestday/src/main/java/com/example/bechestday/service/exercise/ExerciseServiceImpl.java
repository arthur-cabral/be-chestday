package com.example.bechestday.service.exercise;

import com.example.bechestday.dto.ExerciseDTO;
import com.example.bechestday.exception.NotFoundException;
import com.example.bechestday.model.Exercise;
import com.example.bechestday.repository.ExerciseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ExerciseDTO> GetAllExercises() {
        List<Exercise> exerciseList = exerciseRepository.findAll();

        return Collections.singletonList(modelMapper.map(exerciseList, ExerciseDTO.class));
    }

    @Override
    public ExerciseDTO GetExerciseById(Long id) throws Exception {
        try{
            return modelMapper.map(FindExerciseById(id), ExerciseDTO.class);
        } catch (NotFoundException ex){
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    private Optional<Exercise> FindExerciseById(Long id) throws NotFoundException {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isEmpty()){
            throw new NotFoundException("Exercicio nao encontrado");
        }
        return exercise;
    }

    @Override
    public ExerciseDTO PostExercise(ExerciseDTO exerciseDTO) throws Exception {
        try{
            Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);
            var newExercise = exerciseRepository.save(exercise);

            return modelMapper.map(newExercise, ExerciseDTO.class);
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public ExerciseDTO PutExercise(ExerciseDTO exerciseDTO) throws Exception {
        try{
            FindExerciseById(exerciseDTO.getExerciseId());

            Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);
            exercise.setExerciseId(exerciseDTO.getExerciseId());
            var updatedExercise = exerciseRepository.save(exercise);

            return modelMapper.map(updatedExercise, ExerciseDTO.class);
        } catch (NotFoundException ex){
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void DeleteExercise(Long id) throws Exception {
        try{
            exerciseRepository.delete(modelMapper.map(FindExerciseById(id), Exercise.class));
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
