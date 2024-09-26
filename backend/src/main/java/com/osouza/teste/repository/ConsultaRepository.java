package com.osouza.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osouza.teste.domain.entity.Consulta;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {}
