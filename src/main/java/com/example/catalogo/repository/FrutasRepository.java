package com.example.catalogo.repository;

import com.example.catalogo.model.FrutasModel;
import com.example.catalogo.model.Tipos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FrutasRepository extends JpaRepository<FrutasModel, Long> {
    Optional<FrutasModel> findByNomeIgnoreCase(String nome);
    Page<FrutasModel> findByTipo(Tipos tipo, Pageable pageable);
    Page<FrutasModel> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    List<FrutasModel> findByRaroTrue();

}
