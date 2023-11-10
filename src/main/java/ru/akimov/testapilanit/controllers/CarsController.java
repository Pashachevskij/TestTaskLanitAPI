package ru.akimov.testapilanit.controllers;

import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.akimov.testapilanit.dto.CarDTO;
import ru.akimov.testapilanit.models.Car;
import ru.akimov.testapilanit.services.CarsService;
import ru.akimov.testapilanit.services.PeopleService;
import ru.akimov.testapilanit.util.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarsController {

    private final CarsService carsService;
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public CarsController(CarsService carsService, PeopleService peopleService, ModelMapper modelMapper) {
        this.carsService = carsService;
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CarDTO carDTO,
                                             BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");

            }
            throw new CarNotCreatedException(errorMsg.toString());
        }

        carsService.save(convertToCar(carDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Car convertToCar(CarDTO carDTO){
        Car car = modelMapper.map(carDTO, Car.class);
        car.setOwner(peopleService.findOne(carDTO.getOwnerId()));
        return car;
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(CarNotCreatedException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(CarAlreadyExistException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(OwnerNotAdultException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(JsonMappingException e){
        return new ResponseEntity<>(e.getOriginalMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(PersonNotFoundException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}
