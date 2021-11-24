package by.goshakrovsh.racestats.controllers;

import by.goshakrovsh.racestats.SessionsService;
import by.goshakrovsh.racestats.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/json")
public class JsonRestController {
    @Autowired
    SessionsService sessionsService;

    @GetMapping
    public @ResponseBody List<Session> getSessionsJson() {
        return sessionsService.getLast10Sessions();
    }
}
