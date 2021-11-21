package by.goshakrovsh.racestats;

import by.goshakrovsh.racestats.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    CarRepository carRepository;

    @GetMapping
    public String GetCars(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "cars";
    }
}
