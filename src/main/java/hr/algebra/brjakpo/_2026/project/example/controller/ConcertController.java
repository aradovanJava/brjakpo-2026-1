package hr.algebra.brjakpo._2026.project.example.controller;

import hr.algebra.brjakpo._2026.project.example.model.Concert;
import hr.algebra.brjakpo._2026.project.example.model.ConcertForm;
import hr.algebra.brjakpo._2026.project.example.service.ConcertService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
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
    public String newConcert(@Valid @ModelAttribute("concertForm") ConcertForm concertForm,
                             BindingResult result,
                             RedirectAttributes redirect) {

        if(result.hasErrors()) {
            return "saveConcert";
        }

        if(Optional.ofNullable(concertForm.getId()).isPresent()) {

            redirect.addFlashAttribute("toast", "Updated " + concertForm.getName() + " concert!");
        }
        else {
            concertService.addConcert(concertForm);
            redirect.addFlashAttribute("toast", "Updated " + concertForm.getName() + " concert!");
        }

        return "redirect:/concerts/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteConcert(@PathVariable("id") Long id,
                                RedirectAttributes redirect) {
        Optional<ConcertForm> concertFormOptional = concertService.getConcertById(id);

        if(Optional.ofNullable(concertFormOptional).isPresent()) {
            concertService.deleteConcert(id);
            redirect.addFlashAttribute("toast", "Concert " + concertFormOptional.get().getName() + " successfully deleted!");
        }
        else {
            redirect.addFlashAttribute("toast", "Concert " + id + " not found!");
        }

        return "redirect:/concerts/list";
    }

    @GetMapping("/filter")
    public String list(@RequestParam(required = false) String concertName,
                       Model model) {
        ConcertForm concertFormFilterCriteria = new ConcertForm();
        concertFormFilterCriteria.setName(concertName);
        List<ConcertForm> filterdConcertFormList = concertService.filterConcerts(concertFormFilterCriteria);
        model.addAttribute("concerts", filterdConcertFormList);
        return "concerts";
    }
}
