package hr.algebra.brjakpo._2026.project.example.repository;

import hr.algebra.brjakpo._2026.project.example.model.Concert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MockConcertRepository implements ConcertRepository {

    private List<Concert> concerts;

    public MockConcertRepository() {
        concerts = new ArrayList<>();

        Concert firstConcert = new Concert(
                1l,
                "Božićni koncert",
                LocalDateTime.of(2026, 12, 24, 8, 0),
                "Hrvatko narodno kazalište");

        Concert secondConcert = new Concert(
                2l,
                "Novogodišnji koncert",
                LocalDateTime.of(2026, 12, 31, 8, 0),
                "Koncertna dvorana Vatroslav Lisinski");

        concerts.add(firstConcert);
        concerts.add(secondConcert);
    }

    @Override
    public List<Concert> findAll() {
        return concerts;
    }

    @Override
    public void save(Concert concert) {
        concert.setId(generateTheNextId());
        concerts.add(concert);
    }

    private Long generateTheNextId() {
        return concerts.stream().map(Concert::getId).max(Long::compareTo).orElse(0L) + 1;
    }
}
