package com.example.TestAgenda.Repository;

import com.example.TestAgenda.Models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
