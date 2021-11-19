package by.goshakrovsh.racestats;

import by.goshakrovsh.racestats.model.Car;
import by.goshakrovsh.racestats.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    CarRepository carRepository;

    @GetMapping("/")
    String startPage(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "index";
    }
}
