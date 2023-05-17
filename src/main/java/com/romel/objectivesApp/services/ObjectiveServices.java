package com.romel.objectivesApp.services;

import com.romel.objectivesApp.ObjectiveRepository;
import com.romel.objectivesApp.models.Objective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class ObjectiveServices {

    @Autowired
    private ObjectiveRepository repository;

    public Iterable<Objective> getAll() {
        return repository.findAll();

    }

    public Optional<Objective> getById(Long id){
        return repository.findById(id);
    }

    public Objective save(Objective objective){
        if(objective.getId() == null){
            objective.setCreatedAt(Instant.now());
        }

        objective.setUpdatedAt(Instant.now());
        return repository.save(objective);
    }

    public void delete(Objective objective){
        repository.delete(objective);
    }





}
