package com.example.catalogo.controller;


import com.example.catalogo.dto.frutasRequestDTO;
import com.example.catalogo.dto.frutasResponseDTO;
import com.example.catalogo.model.Tipos;
import com.example.catalogo.service.frutasService;
import jakarta.servlet.http.PushBuilder;
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
public class frutasController {

    @Autowired private frutasService service;

    @GetMapping
    public ResponseEntity<Page<frutasResponseDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Tipos tipos,
            @RequestParam(required = false) String nome
    ) {
        Pageable p = PageRequest.of(page, size,Sort.by("nome").ascending());
        return ResponseEntity.ok(service.listAll(p,tipos,nome));
    }

    @GetMapping("/raras")
    public ResponseEntity<List<frutasResponseDTO>> raras() {
        return ResponseEntity.ok(service,listRaras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<frutasResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<frutasResponseDTO> create(@Valid @RequestBody frutasRequestDTO dto) {
        frutasResponseDTO created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/v1/frutas" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<frutasResponseDTO> update(@PathVariable Long id, @Valid @RequestBody frutasRequestDTO dto){
        return ResponseEntity.ok(service.update(id,dto));
    }
    @PatchMapping("/{id}/transfer")
    @PreAuthorize("hasRoles('ADMIN')")
    public ResponseEntity<frutasResponseDTO> transfer(@PathVariable Long id,
                                                      @RequestBody java.util.Map<String, String> body) {
        String novo = body.get("novoUsuario");
        return ResponseEntity.ok(service.transferirUsuario(id, novo));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRoles('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
