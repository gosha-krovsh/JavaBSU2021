package by.goshakrovsh.racestats.controllers;

import by.goshakrovsh.racestats.SessionsService;
import by.goshakrovsh.racestats.model.Session;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
