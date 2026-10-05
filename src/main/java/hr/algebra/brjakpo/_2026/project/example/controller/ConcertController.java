package hr.algebra.brjakpo._2026.project.example.controller;

import hr.algebra.brjakpo._2026.project.example.model.Concert;
import hr.algebra.brjakpo._2026.project.example.service.ConcertService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ConcertController {

    private ConcertService concertService;

    @GetMapping("/list")
    public String getConcerts(Model model) {
        model.addAttribute("concerts", concertService.getConcerts());
        return "concerts";
    }

}
