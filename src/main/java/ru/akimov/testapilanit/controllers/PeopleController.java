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
import ru.akimov.testapilanit.dto.PersonDTO;
import ru.akimov.testapilanit.dto.PersonWithCarsDTO;
import ru.akimov.testapilanit.models.Person;
import ru.akimov.testapilanit.services.PeopleService;
import ru.akimov.testapilanit.util.*;

import java.util.List;

@RestController
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/person")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO,
                                             BindingResult bindingResult){


        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");

            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }

        peopleService.save(convertToPerson(personDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/clear")
    public ResponseEntity<HttpStatus> clear(){
        peopleService.delete();
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/personwithcars")
    public PersonWithCarsDTO getPersonWithCar(@RequestParam(value = "personid", required = false) String personid){
         if(personid == null)
             throw new ParameterIsRequiredException("Parameter personid is required");
         else if (!personid.matches("[0-9]+")) {
             throw new ParameterCantBeStringException("Parameter may contains only number");
         }
        return modelMapper.map(peopleService.findOne(Long.parseLong(personid)), PersonWithCarsDTO.class);
    }


    private Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(PersonNotCreatedException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(PersonAlreadyExistException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(PersonNotFoundException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(DateFormatException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(JsonMappingException e){
        return new ResponseEntity<>(e.getOriginalMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(ParameterIsRequiredException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(ParameterCantBeStringException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }


}
