package ru.akimov.testapilanit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;
import ru.akimov.testapilanit.util.LocalDateDeserializer;


import java.time.LocalDate;



public class PersonDTO {

    @Min(value = 1, message = "Id must be greater than 0")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private long id;
    @NotNull(message = "Name cant be null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must not contain numbers")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String name;
    @NotNull(message = "birth date cant be null")
    @Past(message = "Birth date must be in past time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Moscow")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthdate;

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

}
