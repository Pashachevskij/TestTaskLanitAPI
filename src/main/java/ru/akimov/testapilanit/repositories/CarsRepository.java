package ru.akimov.testapilanit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akimov.testapilanit.models.Car;

import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {
    long findDistinctByModel(String vendor);
}
