package hr.algebra.brjakpo._2026.project.example.service;

import hr.algebra.brjakpo._2026.project.example.model.ConcertForm;

import java.util.List;
import java.util.Optional;

public interface ConcertService {
    List<ConcertForm> getConcerts();
    Optional<ConcertForm> getConcertById(Long id);
    void addConcert(ConcertForm concert);
    void deleteConcert(Long concertId);
}
