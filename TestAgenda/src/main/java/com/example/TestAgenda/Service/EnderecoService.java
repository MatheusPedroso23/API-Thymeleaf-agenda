package com.example.TestAgenda.Service;

import com.example.TestAgenda.Models.Endereco;
import com.example.TestAgenda.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // Método para listar todas as agendas
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    // Método para buscar uma agenda por ID
    public Optional<Endereco> findOne(Long id) {
        return enderecoRepository.findById(id);
    }

    // Método para salvar ou atualizar uma agenda
    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    // Método para deletar uma agenda por ID
    public void delete(Long id) {
        enderecoRepository.deleteById(id);
    }
}
