package hr.algebra.brjakpo._2026.project.example.controller;

import hr.algebra.brjakpo._2026.project.example.model.Concert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("concerts")
public class ConcertController {

    @GetMapping("/list")
    public String getConcerts(Model model) {

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

        model.addAttribute("concerts", concerts);

        return "concerts";
    }

}
