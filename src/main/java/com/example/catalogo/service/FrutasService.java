package com.example.catalogo.service;

import com.example.catalogo.dto.FrutasRequestDTO;
import com.example.catalogo.dto.FrutasResponseDTO;
import com.example.catalogo.model.Tipos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FrutasService {
    FrutasResponseDTO create(FrutasRequestDTO dto);
    FrutasResponseDTO update(Long id, FrutasRequestDTO dto);
    void delete(Long id);
    FrutasResponseDTO getById(Long id);
    Page<FrutasResponseDTO> listAll(Pageable pageable, Tipos tiposmodel, String nomeContains);
    List<FrutasResponseDTO> listRaras();
    FrutasResponseDTO transferirUsuario(Long id, String novoUsuario);



}
