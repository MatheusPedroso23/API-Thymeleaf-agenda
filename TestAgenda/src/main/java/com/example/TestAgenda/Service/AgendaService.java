package com.example.TestAgenda.Services;

import com.example.TestAgenda.Models.Agenda;
import com.example.TestAgenda.Models.Status;
import com.example.TestAgenda.Repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;


    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> findOne(Long id) {
        return agendaRepository.findById(id);
    }

    public Agenda save(Agenda agenda) {
        if (agenda.getId() != null) {
            Optional<Agenda> agendaExistente = agendaRepository.findById(agenda.getId());

            if (agendaExistente.isPresent()) {
                if (agendaExistente.get().getStatus() == Status.FINALIZADO) {
                    throw new IllegalStateException("Não é permitido excluir ou editar uma agenda com status FINALIZADO.");
                }
            }
        }
        return agendaRepository.save(agenda);
    }


    public void delete(Long id) {
        Optional<Agenda> agenda = agendaRepository.findById(id);

        if (agenda.isPresent() && agenda.get().getStatus() == Status.FINALIZADO) {
            throw new IllegalStateException("Não é permitido excluir ou editar uma agenda com status FINALIZADO.");
        }

        agendaRepository.deleteById(id);
    }

}
