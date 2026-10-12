package hr.algebra.brjakpo._2026.project.example.service;

import hr.algebra.brjakpo._2026.project.example.model.Concert;

import java.util.List;
import java.util.Optional;

public interface ConcertService {
    List<Concert> getConcerts();
    Optional<Concert> getConcertById(Long id);
    void addConcert(Concert concert);
    void deleteConcert(Concert concert);
}
