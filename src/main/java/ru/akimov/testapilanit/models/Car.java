package ru.akimov.testapilanit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Car")
public class Car {

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "model")
    private String model;
    @Column(name = "horsepower")
    private int horsepower;
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIgnore
    private Person owner;

    public Car() {
    }

    public Car(long id, String model, int horsepower, Person owner) {
        this.id = id;
        this.model = model;
        this.horsepower = horsepower;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", horsepower=" + horsepower +
                '}';
    }
}
