package com.example.TestAgenda.Services;

import com.example.TestAgenda.Models.Agenda;
import com.example.TestAgenda.Repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    // Método para listar todas as agendas
    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    // Método para buscar uma agenda por ID
    public Optional<Agenda> findOne(Long id) {
        return agendaRepository.findById(id);
    }

    // Método para salvar ou atualizar uma agenda
    public Agenda save(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    // Método para deletar uma agenda por ID
    public void delete(Long id) {
        agendaRepository.deleteById(id);
    }
}
