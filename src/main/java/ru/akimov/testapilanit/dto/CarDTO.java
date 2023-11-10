package ru.akimov.testapilanit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CarDTO {

    @Min(value = 1, message = "Id must be greater than 0")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private long id;
    @NotNull(message = "model cant be null")
    @Pattern(regexp = "^[^-\\d]+-[^-]+$", message = "model must be in format " +
            "vendor-model and vendor or model cant be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String model;
    @NotNull(message = "horsepower cant be null")
    @Min(value = 1, message = "Horse power must be greater than 0")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private int horsepower;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @Min(value = 1, message = "Id must be greater than 0")
    private long ownerId;

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

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
