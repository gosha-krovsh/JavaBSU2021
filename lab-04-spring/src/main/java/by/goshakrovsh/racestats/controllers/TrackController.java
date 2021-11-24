package by.goshakrovsh.racestats.controllers;

import by.goshakrovsh.racestats.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TrackController {
    @Autowired
    TrackRepository trackRepository;

    @GetMapping("/tracks")
    String getTracks(Model model) {
        model.addAttribute("tracks", trackRepository.findAll());
        return "tracks";
    }

    @GetMapping("/track/{id}")
    String getTrack(Model model, @PathVariable Integer id) {
        model.addAttribute("track", trackRepository.findById(id).get());
        return "track";
    }
}
