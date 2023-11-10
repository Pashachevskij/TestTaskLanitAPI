package ru.akimov.testapilanit.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id", unique = true)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthdate")
    private LocalDate birthdate;
    @OneToMany(mappedBy = "owner")
    private List<Car> cars;

    public Person() {
    }

    public Person(long id, String name, LocalDate birthdate, List<Car> cars) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.cars = cars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
