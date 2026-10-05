package hr.algebra.brjakpo._2026.project.example.repository;

import hr.algebra.brjakpo._2026.project.example.model.Concert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MockConcertRepository implements ConcertRepository {

    @Override
    public List<Concert> findAll() {

        Concert firstConcert = new Concert(
                "Božićni koncert",
                LocalDateTime.of(2026, 12, 24, 8, 0),
                "Hrvatko narodno kazalište");

        Concert secondConcert = new Concert(
                "Novogodišnji koncert",
                LocalDateTime.of(2026, 12, 31, 8, 0),
                "Koncertna dvorana Vatroslav Lisinski");

        List<Concert> concerts = new ArrayList<Concert>();
        concerts.add(firstConcert);
        concerts.add(secondConcert);

        return concerts;
    }
}
