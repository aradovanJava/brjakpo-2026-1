package hr.algebra.brjakpo._2026.project.example.service;

import hr.algebra.brjakpo._2026.project.example.model.Concert;
import hr.algebra.brjakpo._2026.project.example.repository.ConcertRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private ConcertRepository concertRepo;

    @Override
    public List<Concert> getConcerts() {
        return concertRepo.findAll();
    }

    @Override
    public void addConcert(Concert concert) {
        concertRepo.save(concert);
    }
}
