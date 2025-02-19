package com.example.repository;

import com.example.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Author: Coutinho
 */
public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
