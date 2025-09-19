package com.example.catalogo.repository;

import com.example.catalogo.model.frutasModel;
import com.example.catalogo.model.Tipos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface frutasRepository extends JpaRepository<frutasModel, Long> {
    Optional<frutasModel> findByNomeIgnoreCase(String nome);
    Page<frutasModel> findByTipo(Tipos tipo, Pageable pageable);
    Page<frutasModel> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    List<frutasModel> findByRaroTrue();

}
