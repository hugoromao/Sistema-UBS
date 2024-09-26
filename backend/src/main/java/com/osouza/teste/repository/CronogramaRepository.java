package com.osouza.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osouza.teste.domain.entity.Cronograma;
import org.springframework.stereotype.Repository;

@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {}
