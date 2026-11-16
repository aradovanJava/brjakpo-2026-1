package hr.algebra.brjakpo._2026.project.example.service;

import hr.algebra.brjakpo._2026.project.example.model.Concert;
import hr.algebra.brjakpo._2026.project.example.model.ConcertForm;
import hr.algebra.brjakpo._2026.project.example.repository.SpringDataJpaConcertRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private SpringDataJpaConcertRepository concertRepo;

    @Override
    public List<ConcertForm> getConcerts() {
        return concertRepo.findAll().stream()
                .map(c -> convertConcertToConcertForm(c))
                .toList();
    }

    @Override
    public List<ConcertForm> filterConcerts(ConcertForm concertFormFilterCriteria) {
        Example<Concert> concertExample = Example.of(convertConcertFormToConcert(concertFormFilterCriteria));
        return concertRepo.findAll(concertExample)
                .stream()
                .map(concert -> convertConcertToConcertForm(concert)).toList();
    }

    @Override
    public Optional<ConcertForm> getConcertById(Long id) {
        return concertRepo.findById(id).map(c -> convertConcertToConcertForm(c));
    }

    @Override
    public void addConcert(ConcertForm concertForm) {
        concertRepo.save(convertConcertFormToConcert(concertForm));
    }

    @Override
    public void deleteConcert(Long concertId) {
        Optional<Concert> concertOptional = concertRepo.findById(concertId);
        if(concertOptional.isPresent()) {
            concertRepo.delete(concertOptional.get());
        }
    }

    @Override
    public void updateConcert(ConcertForm concertFormForUpdate, Long concertId) {
        Optional<Concert> concertToUpdate = concertRepo.findById(concertId);

        if(concertToUpdate.isPresent()) {
            Concert concert = concertToUpdate.get();
            concert.setName(concertFormForUpdate.getName());
            concert.setVenue(concertFormForUpdate.getVenue());
            concert.setStartDateTime(concertFormForUpdate.getStartDateTime());
            concertRepo.save(concert);
        }
    }

    private Concert convertConcertFormToConcert(ConcertForm form) {
        Concert newConcert = new Concert();
        newConcert.setId(form.getId());
        newConcert.setName(form.getName());
        newConcert.setVenue(form.getVenue());
        newConcert.setStartDateTime(form.getStartDateTime());
        return newConcert;
    }

    private ConcertForm convertConcertToConcertForm(Concert concert) {
        ConcertForm form = new ConcertForm();
        form.setId(concert.getId());
        form.setName(concert.getName());
        form.setVenue(concert.getVenue());
        form.setStartDateTime(concert.getStartDateTime());
        return form;
    }
}
