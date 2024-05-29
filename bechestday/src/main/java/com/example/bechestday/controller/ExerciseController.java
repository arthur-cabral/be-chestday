package com.example.bechestday.controller;

import com.example.bechestday.dto.ExerciseDTO;
import com.example.bechestday.exception.NotFoundException;
import com.example.bechestday.service.exercise.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<List<ExerciseDTO>> getAllExercises(){
        List<ExerciseDTO> exerciseList = exerciseService.getAllExercises();
        return ResponseEntity.ok().body(exerciseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable Long id) {
        try{
            ExerciseDTO exercise = exerciseService.getExerciseById(id);
            return ResponseEntity.ok().body(exercise);
        } catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ExerciseDTO> postExercise(@RequestBody ExerciseDTO exerciseDTO){
        try{
            ExerciseDTO exercise = exerciseService.postExercise(exerciseDTO);
            return ResponseEntity.ok().body(exercise);
        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseDTO> putExercise(@PathVariable Long id, @RequestBody ExerciseDTO exerciseDTO){
        try{
            exerciseDTO.setExerciseId(id);
            ExerciseDTO exercise = exerciseService.putExercise(exerciseDTO);
            return ResponseEntity.ok().body(exercise);
        } catch (NotFoundException ex){
            return ResponseEntity.notFound().build();
        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExerciseDTO> deleteExercise(@PathVariable Long id){
        try{
            exerciseService.deleteExercise(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}