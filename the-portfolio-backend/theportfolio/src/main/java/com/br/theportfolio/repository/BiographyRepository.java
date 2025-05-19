package com.br.theportfolio.repository;

import com.br.theportfolio.model.Biography;
import com.br.theportfolio.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiographyRepository extends JpaRepository<Biography, Long> {
}
