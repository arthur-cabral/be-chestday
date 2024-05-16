package com.example.bechestday.model;

import com.example.bechestday.enums.MuscularGroup;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;

    @Column(name = "name")
    private String name;

    @Column(name = "exercise_load")
    private Float exerciseLoad;

    @Column(name = "exercise_sets")
    private Integer exerciseSets;

    @Column(name = "spot_needed")
    private Boolean spotNeeded;

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "muscular_group")
    private MuscularGroup muscularGroup;

    @ManyToMany(mappedBy = "exercises")
    private List<Training> trainings;

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

    public Float getExerciseLoad() {
        return exerciseLoad;
    }

    public void setExerciseLoad(Float exerciseLoad) {
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

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }
}
