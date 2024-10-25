package com.example.TestAgenda.Service;

import com.example.TestAgenda.Models.Usuario;
import com.example.TestAgenda.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para listar todas as agendas
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Método para buscar uma agenda por ID
    public Optional<Usuario> findOne(Long id) {
        return usuarioRepository.findById(id);
    }

    // Método para salvar ou atualizar uma agenda
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Método para deletar uma agenda por ID
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
