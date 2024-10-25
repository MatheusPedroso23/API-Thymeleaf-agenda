package com.example.TestAgenda.Repository;

import com.example.TestAgenda.Models.TipoAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAcessoRepository extends JpaRepository<TipoAcesso, Long> {
}
