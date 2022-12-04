package pl.wj.sqldemo.car.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String brand;

    private String model;

    @Enumerated(value = EnumType.ORDINAL)
    private Color color;

    @Column(name = "production_date")
    @Temporal(TemporalType.DATE)
    private Date productionDate;

    public Car() {
    }

    public Car(long id, String brand, String model, Color color, Date productionDate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.productionDate = productionDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
}
