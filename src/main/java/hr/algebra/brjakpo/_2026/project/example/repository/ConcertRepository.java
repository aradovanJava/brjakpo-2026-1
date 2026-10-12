package hr.algebra.brjakpo._2026.project.example.repository;

import hr.algebra.brjakpo._2026.project.example.model.Concert;

import java.util.List;
import java.util.Optional;

public interface ConcertRepository {
    List<Concert> findAll();
    Optional<Concert> findById(Long id);
    void save(Concert concert);
    void delete(Concert concert);
}
