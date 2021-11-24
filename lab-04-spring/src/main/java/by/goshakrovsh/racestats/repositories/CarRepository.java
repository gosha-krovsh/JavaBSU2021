package by.goshakrovsh.racestats.repositories;

import by.goshakrovsh.racestats.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {
    Car getCarByManufacturerAndModelAndGeneration(String manufacturer,
                                                  String model,
                                                  String generation);
}
