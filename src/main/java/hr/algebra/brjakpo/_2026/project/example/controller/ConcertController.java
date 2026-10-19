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
        return "saveConcert";
    }

    @GetMapping("/update/{id}")
    public String getUpdateConcert(Model model, @PathVariable Long id) {
        Optional<ConcertForm> concertFromOptional = concertService.getConcertById(id);

        if(concertFromOptional.isPresent()) {
            ConcertForm concertForm = concertFromOptional.get();
            model.addAttribute("concertForm", concertForm);
            return "saveConcert";
        }
        else {
            //TODO add an error message that explains that the concert with the given ID does not exist
            return "redirect:/concerts/list";
        }
    }

    @PostMapping("/new")
    public String newConcert(@ModelAttribute("concertForm") ConcertForm concertForm) {
        concertService.addConcert(concertForm);
        return "redirect:/concerts/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteConcert(@PathVariable("id") Long id) {
        concertService.deleteConcert(id);
        //TODO check if the concert with the given ID exists
        return "redirect:/concerts/list";
    }
}
