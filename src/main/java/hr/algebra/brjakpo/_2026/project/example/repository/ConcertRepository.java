package hr.algebra.brjakpo._2026.project.example.repository;

import hr.algebra.brjakpo._2026.project.example.model.Concert;

import java.util.List;

public interface ConcertRepository {
    List<Concert> findAll();
    void save(Concert concert);
}
