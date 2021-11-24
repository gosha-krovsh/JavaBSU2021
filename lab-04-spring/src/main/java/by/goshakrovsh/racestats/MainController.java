package by.goshakrovsh.racestats;

import by.goshakrovsh.racestats.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SessionsService service;

    @GetMapping("/")
    String startPage(Model model) {
        model.addAttribute("sessions",
                service.getLast10Sessions());
        return "index";
    }

    @GetMapping("/sessions")
    String sessionPage(Model model) {
        model.addAttribute("sessions",
                sessionRepository.findAll());
        return "sessions";
    }
}
