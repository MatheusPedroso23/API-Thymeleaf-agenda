package com.example.TestAgenda.Service;

import com.example.TestAgenda.Models.Servico;
import com.example.TestAgenda.Repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    // Método para listar todas as agendas
    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    // Método para buscar uma agenda por ID
    public Optional<Servico> findOne(Long id) {
        return servicoRepository.findById(id);
    }

    // Método para salvar ou atualizar uma agenda
    public Servico save(Servico servico) {
        return servicoRepository.save(servico);
    }

    // Método para deletar uma agenda por ID
    public void delete(Long id) {
        servicoRepository.deleteById(id);
    }
}
