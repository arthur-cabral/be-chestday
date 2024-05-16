package com.example.bechestday.controller;

import com.example.bechestday.dto.TrainingDTO;
import com.example.bechestday.exception.NotFoundException;
import com.example.bechestday.service.training.TrainingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("training")
public class TrainingController {
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping
    public ResponseEntity<List<TrainingDTO>> GetAllTrainings(){
        List<TrainingDTO> trainingList = trainingService.GetAllTrainings();
        return ResponseEntity.ok().body(trainingList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingDTO> GetTrainingById(@PathVariable Long id){
        try {
            TrainingDTO trainingList = trainingService.GetTrainingById(id);
            return ResponseEntity.ok().body(trainingList);
        } catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<TrainingDTO> PostTraining(@RequestBody TrainingDTO trainingDTO){
        try {
            TrainingDTO trainingList = trainingService.PostTraining(trainingDTO);
            return ResponseEntity.ok().body(trainingList);
        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingDTO> PutTraining(@PathVariable Long id, @RequestBody TrainingDTO trainingDTO){
        try {
            TrainingDTO trainingList = trainingService.PutTraining(trainingDTO);
            return ResponseEntity.ok().body(trainingList);
        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrainingDTO> DeleteTraining(@PathVariable Long id){
        try {
            trainingService.DeleteTraining(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
}
