package pl.wj.sqldemo.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wj.sqldemo.car.entity.Car;
import pl.wj.sqldemo.car.entity.Color;
import pl.wj.sqldemo.car.repo.CarRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarService {

    private CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public List<Car> getAll() {
        return carRepo.findAll();
    }

    public List<Car> getByYear(int yearFrom, int yearTo) {
        try {
            String dateFrom = yearFrom + "-01-01";
            String dateTo = yearTo + "-12-31";
            Date myDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
            Date myDate2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);
            return carRepo.getCarsByProductionDateBetweenFromAndTo(myDate1, myDate2);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Car> getByYearAndFilter(int yearFrom, int yearTo, String filter) {
        try {
            String dateFrom = yearFrom + "-01-01";
            String dateTo = yearTo + "-12-31";
            Date myDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
            Date myDate2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);
            return carRepo.getCarsByProductionDateBetweenFromAndToAndFilter(myDate1, myDate2, filter);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Car> getByBrand(String filter) {
        return carRepo.getCarsByBrand(filter);
    }

    public List<Car> getByFilter(String filter) {
        return carRepo.getCarsByFilter(filter);
    }

    public List<Car> getByColor(String color) {
        int ordinal = Color.valueOf(color.toUpperCase()).ordinal();
        return carRepo.getCarsByColor(ordinal);
    }

    public Car getById(int id){
        return carRepo.getCarById(id);
    }
}
