package com.example.catalogo.service;

import com.example.catalogo.dto.frutasRequestDTO;
import com.example.catalogo.dto.frutasResponseDTO;
import com.example.catalogo.model.Tipos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface frutasService {
    frutasResponseDTO create(frutasRequestDTO dto);
    frutasResponseDTO update(Long id, frutasRequestDTO dto);
    void delete(Long id);
    frutasResponseDTO getById(Long id);
    Page<frutasResponseDTO> listAll(Pageable pageable, Tipos tiposmodel, String nomeContains);
    List<frutasResponseDTO> listRaras();
    frutasResponseDTO transferirUsuario(Long id, String novoUsuario);



}
