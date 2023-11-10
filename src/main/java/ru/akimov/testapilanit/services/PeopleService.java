package ru.akimov.testapilanit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akimov.testapilanit.models.Person;
import ru.akimov.testapilanit.repositories.PeopleRepository;
import ru.akimov.testapilanit.util.PersonAlreadyExistException;
import ru.akimov.testapilanit.util.PersonNotFoundException;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Person findOne(long id){
        return peopleRepository.findById(id).
                orElseThrow(() -> new PersonNotFoundException("Person with id " + id + " not found"));
    }

    @Transactional
    public void save(Person person){
        if (peopleRepository.existsById(person.getId()))
            throw new PersonAlreadyExistException("Person with id " + person.getId() + " is already exist");
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(){
        peopleRepository.deleteAll();
    }

}
