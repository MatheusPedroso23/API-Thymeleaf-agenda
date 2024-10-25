package com.example.TestAgenda.Service;

import com.example.TestAgenda.Models.Funcionario;
import com.example.TestAgenda.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Método para listar todas as agendas
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    // Método para buscar uma agenda por ID
    public Optional<Funcionario> findOne(Long id) {
        return funcionarioRepository.findById(id);
    }

    // Método para salvar ou atualizar uma agenda
    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    // Método para deletar uma agenda por ID
    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
