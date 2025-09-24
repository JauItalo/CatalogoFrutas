package com.example.catalogo.controller;


import com.example.catalogo.dto.FrutasRequestDTO;
import com.example.catalogo.dto.FrutasResponseDTO;
import com.example.catalogo.model.Tipos;
import com.example.catalogo.service.FrutasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/frutas")
public class FrutasController {

    @Autowired private FrutasService service;

    @GetMapping
    public ResponseEntity<Page<FrutasResponseDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Tipos tipos,
            @RequestParam(required = false) String nome
    ) {
        Pageable p = PageRequest.of(page, size,Sort.by("nome").ascending());
        return ResponseEntity.ok(service.listAll(p,tipos,nome));
    }

    @GetMapping("/raras")
    public ResponseEntity<List<FrutasResponseDTO>> raras() {
        return ResponseEntity.ok(service.listRaras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FrutasResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FrutasResponseDTO> create(@Valid @RequestBody FrutasRequestDTO dto) {
        FrutasResponseDTO created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/v1/frutas" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FrutasResponseDTO> update(@PathVariable Long id, @Valid @RequestBody FrutasRequestDTO dto){
        return ResponseEntity.ok(service.update(id,dto));
    }
    @PatchMapping("/{id}/transfer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FrutasResponseDTO> transfer(@PathVariable Long id,
                                                      @RequestBody java.util.Map<String, String> body) {
        String novo = body.get("novoUsuario");
        return ResponseEntity.ok(service.transferirUsuario(id, novo));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
