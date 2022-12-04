package pl.wj.sqldemo.car.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.wj.sqldemo.car.entity.Car;
import pl.wj.sqldemo.car.entity.Product;

import java.util.Date;
import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    List<Car> findAll();

    Product saveAndFlush(Product product);

    @Query(value = "SELECT * FROM Cars", nativeQuery = true)
    List<Car> getAllCars();

    @Query(value = "select * FROM cars c WHERE c.production_date between ?1 AND ?2", nativeQuery = true)
    List<Car> getCarsByProductionDateBetweenFromAndTo(Date dateFrom, Date dateTo);

    @Query(value = "select * FROM cars c WHERE c.production_date between ?1 AND ?2 and (regexp_like(c.brand, ?3, 'i') or regexp_like(c.model, ?3, 'i'))", nativeQuery = true)
    List<Car> getCarsByProductionDateBetweenFromAndToAndFilter(Date dateFrom, Date dateTo, String filter);

    @Query(value = "select * FROM cars c WHERE LOWER(c.brand) LIKE LOWER(CONCAT('%', ?1,'%'))", nativeQuery = true)
    List<Car> getCarsByBrand(String brand);

    @Query(value = "select * FROM cars c WHERE regexp_like(c.brand, ?1, 'i') or regexp_like(c.model, ?1, 'i')", nativeQuery = true)
    List<Car> getCarsByFilter(String filter);

    @Query(value = "select * FROM cars c WHERE c.color = ?1", nativeQuery = true)
    List<Car> getCarsByColor(int color);

    @Query(value = "select * FROM cars c WHERE c.id = ?1", nativeQuery = true)
    Car getCarById(int id);
}
