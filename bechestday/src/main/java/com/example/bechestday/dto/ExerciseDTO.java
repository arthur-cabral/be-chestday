package com.example.bechestday.dto;

import com.example.bechestday.enums.MuscularGroup;

import java.util.Date;
import java.util.List;

public class ExerciseDTO {
    private Long exerciseId;
    private String name;
    private Date dateCreated;
    private MuscularGroup muscularGroup;
    private Double exerciseLoad;
    private Integer exerciseSets;
    private Boolean spotNeeded;
    private List<TrainingDTO> trainings;

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public MuscularGroup getMuscularGroup() {
        return muscularGroup;
    }

    public void setMuscularGroup(MuscularGroup muscularGroup) {
        this.muscularGroup = muscularGroup;
    }

    public Double getExerciseLoad() {
        return exerciseLoad;
    }

    public void setExerciseLoad(Double exerciseLoad) {
        this.exerciseLoad = exerciseLoad;
    }

    public Integer getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(Integer exerciseSets) {
        this.exerciseSets = exerciseSets;
    }

    public Boolean getSpotNeeded() {
        return spotNeeded;
    }

    public void setSpotNeeded(Boolean spotNeeded) {
        this.spotNeeded = spotNeeded;
    }

    public List<TrainingDTO> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<TrainingDTO> trainings) {
        this.trainings = trainings;
    }
}
