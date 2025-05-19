package com.br.theportfolio.service;

import com.br.theportfolio.model.Biography;
import com.br.theportfolio.repository.BiographyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BiographyService {

    private final BiographyRepository repository;

    public BiographyService(BiographyRepository repository) {
        this.repository = repository;
    }

    public Biography save(Biography biography) {
        return repository.save(biography);
    }

    public Optional<Biography> getBio(Long id) {
        return repository.findById(id);
    }
}
