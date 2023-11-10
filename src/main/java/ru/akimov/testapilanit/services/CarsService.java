package ru.akimov.testapilanit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akimov.testapilanit.models.Car;
import ru.akimov.testapilanit.repositories.CarsRepository;
import ru.akimov.testapilanit.util.CarAlreadyExistException;
import ru.akimov.testapilanit.util.OwnerNotAdultException;

import java.time.LocalDate;
import java.time.Period;

@Service
@Transactional(readOnly = true)
public class CarsService {

    private final CarsRepository carsRepository;

    @Autowired
    public CarsService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Transactional
    public void save(Car car){
        if (carsRepository.existsById(car.getId()))
           throw new CarAlreadyExistException("Car with id " + car.getId() + " already exist");
        else if (!((Period.between(car.getOwner().getBirthdate(),LocalDate.now()).getYears())>=18)){
            throw new OwnerNotAdultException("Owner cant be under 18 y.o");
        }
        carsRepository.save(car);
    }


}
