package ru.akimov.testapilanit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akimov.testapilanit.models.Statistic;
import ru.akimov.testapilanit.repositories.CarsRepository;
import ru.akimov.testapilanit.repositories.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class StatisticsService {

    private final PeopleRepository peopleRepository;
    private final CarsRepository carsRepository;

    @Autowired
    public StatisticsService(PeopleRepository peopleRepository, CarsRepository carsRepository) {
        this.peopleRepository = peopleRepository;
        this.carsRepository = carsRepository;
    }

    public long personCount(){
        return peopleRepository.count();
    }

    public long carCount(){
        return carsRepository.count();
    }

    public long uniqueVendorCount(){
        return carsRepository.findAll().stream().
                map(car -> car.getModel().toLowerCase().
                        split("-")[0]).distinct().count();
    }

    public Statistic enrichStatistic(Statistic statistic){
        statistic.setPersonCount(personCount());
        statistic.setCarCount(carCount());
        statistic.setUniqueVendorCount(uniqueVendorCount());
        return statistic;
    }
}
