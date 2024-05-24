package com.example.bechestday.controller;

import com.example.bechestday.dto.TrainingDTO;
import com.example.bechestday.service.training.TrainingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping
    public ResponseEntity<List<TrainingDTO>> getAllTrainings(){
        List<TrainingDTO> trainingList = trainingService.getAllTrainings();
        return ResponseEntity.ok().body(trainingList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingDTO> getTrainingById(@PathVariable Long id){
        try {
            TrainingDTO trainingList = trainingService.getTrainingById(id);
            return ResponseEntity.ok().body(trainingList);
        } catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> postTraining(@RequestBody TrainingDTO trainingDTO){
        try {
            TrainingDTO trainingList = trainingService.postTraining(trainingDTO);
            return ResponseEntity.ok().body(trainingList);
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingDTO> putTraining(@PathVariable Long id, @RequestBody TrainingDTO trainingDTO){
        try {
            trainingDTO.setTrainingId(id);
            TrainingDTO trainingList = trainingService.putTraining(trainingDTO);
            return ResponseEntity.ok().body(trainingList);
        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrainingDTO> deleteTraining(@PathVariable Long id){
        try {
            trainingService.deleteTraining(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
}
