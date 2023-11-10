package ru.akimov.testapilanit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import ru.akimov.testapilanit.models.Car;

import java.time.LocalDate;
import java.util.List;

public class PersonWithCarsDTO {

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Moscow")
    private LocalDate birthdate;
    @NotNull(message = "array cant be null")
    private List<Car> cars;

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
