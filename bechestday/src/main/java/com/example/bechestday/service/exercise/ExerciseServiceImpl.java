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
    public List<ExerciseDTO> getAllExercises() {
        List<Exercise> exerciseList = exerciseRepository.findAll();

        return Collections.singletonList(modelMapper.map(exerciseList, ExerciseDTO.class));
    }

    @Override
    public ExerciseDTO getExerciseById(Long id) throws Exception {
        try{
            return modelMapper.map(findExerciseById(id), ExerciseDTO.class);
        } catch (NotFoundException ex){
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    private Optional<Exercise> findExerciseById(Long id) throws NotFoundException {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isEmpty()){
            throw new NotFoundException("Exercicio nao encontrado");
        }
        return exercise;
    }

    @Override
    public ExerciseDTO postExercise(ExerciseDTO exerciseDTO) throws Exception {
        try{
            Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);
            var newExercise = exerciseRepository.save(exercise);

            return modelMapper.map(newExercise, ExerciseDTO.class);
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public ExerciseDTO putExercise(ExerciseDTO exerciseDTO) throws Exception {
        try{
            findExerciseById(exerciseDTO.getExerciseId());

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
    public void deleteExercise(Long id) throws Exception {
        try{
            exerciseRepository.delete(modelMapper.map(findExerciseById(id), Exercise.class));
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
