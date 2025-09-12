package com.example.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.catalogo.model.Role;
import java.util.Optional;

public interface roleRepository extends JpaRepository <Role, Long> {
    Optional<Role> findByName(String name);
}
