package by.goshakrovsh.racestats.controllers;

import by.goshakrovsh.racestats.SessionsService;
import by.goshakrovsh.racestats.model.Car;
import by.goshakrovsh.racestats.model.Session;
import by.goshakrovsh.racestats.model.Track;
import by.goshakrovsh.racestats.repositories.CarRepository;
import by.goshakrovsh.racestats.repositories.SessionRepository;
import by.goshakrovsh.racestats.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class AdminPanelController {
    @Autowired
    CarRepository carRepository;

    @Autowired
    TrackRepository trackRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SessionsService sessionsService;

    @GetMapping("/admin")
    public String getPanel(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("tracks", trackRepository.findAll());
        model.addAttribute("allTyres", Session.Tyre.values());
        model.addAttribute("allConditions", Session.Conditions.values());
        return "admin";
    }

    @PostMapping("/admin/car")
    public String postCar(@RequestParam String Manufacturer,
                          @RequestParam String Model,
                          @RequestParam String Generation) {
        carRepository.save(new Car(Manufacturer, Model, Generation));
        return "redirect:/cars";
    }

    @PostMapping("/admin/track")
    public String postCar(@RequestParam String Name,
                          @RequestParam String Image,
                          @RequestParam String Location,
                          @RequestParam String Record) {
        trackRepository.save(new Track(Name, Image, Location, Record.isEmpty() ? null : Integer.valueOf(Record)));
        return "redirect:/tracks";
    }

    @PostMapping("/admin/session")
    public String postSession(@RequestParam String car_id,
                              @RequestParam String track_id,
                              @RequestParam String time,
                              @RequestParam
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                              @RequestParam String tyre,
                              @RequestParam String conditions,
                              @RequestParam String description) {
        Session session = new Session();
        session.setCar(carRepository.findById(Integer.valueOf(car_id)).get());
        session.setTrack(trackRepository.findById(Integer.valueOf(track_id)).get());
        session.setFormatTime(time);
        session.setDate_time(Timestamp.valueOf(date));

        session.setTyre(Session.Tyre.valueOf(tyre));
        session.setConditions(Session.Conditions.valueOf(conditions));

        session.setDescription(description);

        sessionsService.UpdateRecord(session);
        sessionRepository.save(session);
        return "redirect:/";
    }
}
