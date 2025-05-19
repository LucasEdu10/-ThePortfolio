package com.br.theportfolio.service;

import com.br.theportfolio.model.Experience;
import com.br.theportfolio.repository.ExperienceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    private final ExperienceRepository repository;

    public ExperienceService(ExperienceRepository repository) {
        this.repository = repository;
    }

    public Experience save(Experience experience) {
        return repository.save(experience);
    }

    public List<Experience> getExperience() {
        return repository.findAll();
    }
}
