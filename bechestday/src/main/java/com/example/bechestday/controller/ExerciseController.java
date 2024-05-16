package com.example.bechestday.controller;

import com.example.bechestday.dto.ExerciseDTO;
import com.example.bechestday.exception.NotFoundException;
import com.example.bechestday.service.exercise.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<List<ExerciseDTO>> GetAllExercises(){
        List<ExerciseDTO> exerciseList = exerciseService.GetAllExercises();
        return ResponseEntity.ok().body(exerciseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO> GetExerciseById(@PathVariable Long id) {
        try{
            ExerciseDTO exercise = exerciseService.GetExerciseById(id);
            return ResponseEntity.ok().body(exercise);
        } catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ExerciseDTO> PostExercise(@RequestBody ExerciseDTO exerciseDTO){
        try{
            ExerciseDTO exercise = exerciseService.PostExercise(exerciseDTO);
            return ResponseEntity.ok().body(exercise);
        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseDTO> PutExercise(@PathVariable Long id, @RequestBody ExerciseDTO exerciseDTO){
        try{
            exerciseDTO.setExerciseId(id);
            ExerciseDTO exercise = exerciseService.PutExercise(exerciseDTO);
            return ResponseEntity.ok().body(exercise);
        } catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExerciseDTO> DeleteExercise(@PathVariable Long id){
        try{
            exerciseService.DeleteExercise(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}