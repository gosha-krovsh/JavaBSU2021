package by.goshakrovsh.racestats;

import by.goshakrovsh.racestats.model.Car;
import by.goshakrovsh.racestats.model.Track;
import by.goshakrovsh.racestats.repositories.CarRepository;
import by.goshakrovsh.racestats.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AdminPanelController {
    @Autowired
    CarRepository carRepository;

    @Autowired
    TrackRepository trackRepository;

    @GetMapping("/admin")
    public String getPanel() {
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
        trackRepository.save(new Track(Name, Image, Location, Integer.valueOf(Record)));
        return "redirect:/tracks";
    }

//    @PostMapping("/admin/session")
//    public String postCar(@RequestParam Tra Name,
//                          @RequestParam String Image,
//                          @RequestParam String Location,
//                          @RequestParam String Record) {
//        trackRepository.save(new Track(Name, Image, Location, Integer.valueOf(Record)));
//        return "redirect:/tracks";
//    }
}
