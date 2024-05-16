package com.example.bechestday.service.training;

import com.example.bechestday.dto.TrainingDTO;
import com.example.bechestday.exception.NotFoundException;
import com.example.bechestday.model.Training;
import com.example.bechestday.repository.TrainingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService{

    private final TrainingRepository trainingRepository;
    private final ModelMapper modelMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, ModelMapper modelMapper) {
        this.trainingRepository = trainingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TrainingDTO> GetAllTrainings() {
        List<Training> trainingList = trainingRepository.findAll();

        return Collections.singletonList(modelMapper.map(trainingList, TrainingDTO.class));
    }

    @Override
    public TrainingDTO GetTrainingById(Long id) throws Exception {
        try {
            return modelMapper.map(FindTrainingById(id), TrainingDTO.class);
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    private Optional<Training> FindTrainingById(Long id) throws NotFoundException {
        Optional<Training> training = trainingRepository.findById(id);
        if (training.isEmpty()){
            throw new NotFoundException("Treino nao encontrado");
        }
        return training;
    }

    @Override
    public TrainingDTO PostTraining(TrainingDTO trainingDTO) throws Exception {
        try{
            Training training = modelMapper.map(trainingDTO, Training.class);
            var newTraining = trainingRepository.save(training);

            return modelMapper.map(newTraining, TrainingDTO.class);
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public TrainingDTO PutTraining(TrainingDTO trainingDTO) throws Exception {
        try {
            FindTrainingById(trainingDTO.getTrainingId());

            Training training = modelMapper.map(trainingDTO, Training.class);
            training.setTrainingId(trainingDTO.getTrainingId());
            var updatedTraining = trainingRepository.save(training);

            return modelMapper.map(updatedTraining, TrainingDTO.class);
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void DeleteTraining(Long id) throws Exception {
        try{
            trainingRepository.delete(modelMapper.map(FindTrainingById(id), Training.class));
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
