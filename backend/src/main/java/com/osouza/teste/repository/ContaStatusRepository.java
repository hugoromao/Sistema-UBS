package com.osouza.teste.repository;

import com.osouza.teste.domain.entity.ContaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaStatusRepository extends JpaRepository<ContaStatus, Long> {}
