package com.example.catalogo.service;


import com.example.catalogo.dto.frutasRequestDTO;
import com.example.catalogo.dto.frutasResponseDTO;
import com.example.catalogo.exception.ResourceNotFoundException;
import com.example.catalogo.model.frutasModel;
import com.example.catalogo.model.Tipos;
import com.example.catalogo.repository.frutasRepository;
import com.example.catalogo.service.frutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class frutasServiceImpl implements frutasService {

    @Autowired
    private frutasRepository repo;

    private frutasResponseDTO toDTO(frutasModel e) {
        frutasResponseDTO r = new frutasResponseDTO();
        r.setId(e.getId()); r.setNome(e.getNome()); r.setTipo(e.getTipos());
        r.setDescricao(e.getDescricao()); r.setResumopoder(e.getResumoPoder());
        r.setUsuarioAtual(e.getUsuarioAtual()); r.setUsuarioAnterior(e.getUsuarioAnterior());
        r.setDataDescoberta(e.getDataDescoberta()); r.setRaro(e.isRaro());
        return r;
    }
    private void apply(frutasModel e, frutasRequestDTO dto) {
        e.setNome(dto.getNome()); e.setTipos(dto.getTiposModel()); e.setDescricao(dto.getDescricao());
        e.setResumoPoder(dto.getResumoPoder()); e.setUsuarioAtual(dto.getUsuarioAtual()); e.setRaro(dto.isRaro());
        if (e.getDataDescoberta() == null) e.setDataDescoberta(LocalDateTime.now());
    }

    @Override
    public frutasResponseDTO create(frutasRequestDTO dto) {
        repo.findByNomeIgnoreCase(dto.getNome()).ifPresent(s -> { throw new IllegalArgumentException("Fruta já existe"); });
        frutasModel e = new frutasModel();
        apply(e, dto);
        return toDTO(repo.save(e));
    }
    @Override
    public frutasResponseDTO update(Long id, frutasRequestDTO dto) {
        frutasModel e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fruta não encontrada"));
        apply(e, dto);
        return toDTO(repo.save(e));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Fruta não encontrada");
        repo.deleteById(id);
    }

    @Override
    public frutasResponseDTO getById(Long id) {
        return repo.findById(id).map(this::toDTO).orElseThrow(() -> new ResourceNotFoundException("Fruta não encontrada"));
    }

    @Override
    public Page<frutasResponseDTO> listAll(Pageable pageable, Tipos tipo, String nomeContains) {
        Page<frutasModel> page;
        if (tipo != null) page = repo.findByTipo(tipo, pageable);
        else if (nomeContains != null && !nomeContains.isBlank()) page = repo.findByNomeContainingIgnoreCase(nomeContains, pageable);
        else page = repo.findAll(pageable);
        return page.map(this::toDTO);
    }

    @Override
    public List<frutasResponseDTO> listRaras() {
        return repo.findByRaroTrue().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public frutasResponseDTO transferirUsuario(Long id, String novoUsuario) {
        frutasModel e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fruta não encontrada"));
        e.setUsuarioAnterior(e.getUsuarioAtual());
        e.setUsuarioAtual(novoUsuario);
        return toDTO(repo.save(e));
    }
}
