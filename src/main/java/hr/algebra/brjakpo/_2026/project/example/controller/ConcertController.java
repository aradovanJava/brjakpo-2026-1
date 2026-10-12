package hr.algebra.brjakpo._2026.project.example.controller;

import hr.algebra.brjakpo._2026.project.example.model.Concert;
import hr.algebra.brjakpo._2026.project.example.model.ConcertForm;
import hr.algebra.brjakpo._2026.project.example.service.ConcertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("concerts")
@AllArgsConstructor
public class ConcertController {

    private ConcertService concertService;

    @GetMapping("/list")
    public String getConcerts(Model model) {
        model.addAttribute("concerts", concertService.getConcerts());
        return "concerts";
    }

    @GetMapping("/new")
    public String getNewConcert(Model model) {
        model.addAttribute("concertForm", new ConcertForm());
        return "newConcert";
    }

    @PostMapping("/new")
    public String newConcert(@ModelAttribute("concertForm") ConcertForm concertForm) {
        Concert newConcert = convertConcertFormToConcert(concertForm);
        concertService.addConcert(newConcert);
        return "redirect:/concerts/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteConcert(@PathVariable("id") Long id) {
        Optional<Concert> concertToBeDeleted = concertService.getConcertById(id);
        if(concertToBeDeleted.isPresent()) {
            concertService.deleteConcert(concertToBeDeleted.get());
        }
        return "redirect:/concerts/list";
    }

    private Concert convertConcertFormToConcert(ConcertForm form) {
        Concert newConcert = new Concert();
        newConcert.setName(form.getName());
        newConcert.setVenue(form.getVenue());
        newConcert.setStartDateTime(form.getStartDateTime());
        return newConcert;
    }

}
