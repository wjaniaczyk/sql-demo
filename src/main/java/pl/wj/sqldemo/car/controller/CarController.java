package pl.wj.sqldemo.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wj.sqldemo.car.entity.Car;
import pl.wj.sqldemo.car.service.CarService;

import java.util.List;

@RestController
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAll(@RequestParam(required = false) Integer yearFrom,
                                         @RequestParam(required = false) Integer yearTo,
                                         @RequestParam(required = false) String filter) {
        List<Car> cars;
        if (yearFrom != null && yearTo != null) {
            if(filter != null){
                cars = carService.getByYearAndFilter(yearFrom, yearTo, filter);
            } else {
                cars = carService.getByYear(yearFrom, yearTo);
            }
        } else if(filter != null){
            cars = carService.getByFilter(filter);
        } else {
            cars = carService.getAll();
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/cars-color")
    public ResponseEntity<List<Car>> getByColor(@RequestParam(required = false) String color) {
        List<Car> cars;
        if (color != null) {
            cars = carService.getByColor(color);
        } else {
            cars = carService.getAll();
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getById(@PathVariable Integer id){
        Car byId = carService.getById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }
}
