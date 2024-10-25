package com.example.TestAgenda.Service;

import com.example.TestAgenda.Models.TipoAcesso;
import com.example.TestAgenda.Repository.TipoAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoAcessoService {

    @Autowired
    private TipoAcessoRepository tipoAcessoRepository;

    // Método para listar todas as agendas
    public List<TipoAcesso> findAll() {
        return tipoAcessoRepository.findAll();
    }

    // Método para buscar uma agenda por ID
    public Optional<TipoAcesso> findOne(Long id) {
        return tipoAcessoRepository.findById(id);
    }

    // Método para salvar ou atualizar uma agenda
    public TipoAcesso save(TipoAcesso tipoAcesso) {
        return tipoAcessoRepository.save(tipoAcesso);
    }

    // Método para deletar uma agenda por ID
    public void delete(Long id) {
        tipoAcessoRepository.deleteById(id);
    }
}
