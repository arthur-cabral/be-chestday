package com.example.bechestday.service.training;

import com.example.bechestday.dto.ExerciseDTO;
import com.example.bechestday.dto.TrainingDTO;
import com.example.bechestday.exception.NotFoundException;
import com.example.bechestday.model.Training;
import com.example.bechestday.repository.ExerciseRepository;
import com.example.bechestday.repository.TrainingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService{

    private final TrainingRepository trainingRepository;
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.trainingRepository = trainingRepository;
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TrainingDTO> getAllTrainings() {
        List<Training> trainingList = trainingRepository.findAll();

        return trainingList.stream()
                .map(training -> modelMapper.map(training, TrainingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TrainingDTO getTrainingById(Long id) throws Exception {
        try {
            return modelMapper.map(findTrainingById(id), TrainingDTO.class);
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    private Optional<Training> findTrainingById(Long id) throws NotFoundException {
        Optional<Training> training = trainingRepository.findById(id);
        if (training.isEmpty()){
            throw new NotFoundException("Treino nao encontrado");
        }
        return training;
    }

    @Override
    public TrainingDTO postTraining(TrainingDTO trainingDTO) throws Exception {
        try{
            List<ExerciseDTO> exercises = getExercisesListById(trainingDTO.getExercises());
            trainingDTO.setExercises(exercises);
            Training training = modelMapper.map(trainingDTO, Training.class);
            var newTraining = trainingRepository.save(training);

            return modelMapper.map(newTraining, TrainingDTO.class);
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    private List<ExerciseDTO> getExercisesListById(List<ExerciseDTO> exerciseList) throws Exception {
        try{
            List<ExerciseDTO> exerciseListWithAttributes = new ArrayList<>();
            if (exerciseList == null || exerciseList.isEmpty()){
                throw new Exception("The exercises list is empty");
            }
            for (ExerciseDTO exerciseDTO : exerciseList){
                var existsExercise = exerciseRepository.existsById(exerciseDTO.getExerciseId());
                if (!existsExercise){
                    throw new Exception("One of the exercises does not exists");
                }
                var exerciseConvertedToModel = exerciseRepository.findById(exerciseDTO.getExerciseId()).get();
                exerciseListWithAttributes.add(modelMapper.map(exerciseConvertedToModel, ExerciseDTO.class));
            }
            return exerciseListWithAttributes;
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public TrainingDTO putTraining(TrainingDTO trainingDTO) throws Exception {
        try {
            findTrainingById(trainingDTO.getTrainingId());

            Training training = modelMapper.map(trainingDTO, Training.class);
            training.setTrainingId(trainingDTO.getTrainingId());
            var updatedTraining = trainingRepository.save(training);

            return modelMapper.map(updatedTraining, TrainingDTO.class);
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void deleteTraining(Long id) throws Exception {
        try{
            trainingRepository.delete(modelMapper.map(findTrainingById(id), Training.class));
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
