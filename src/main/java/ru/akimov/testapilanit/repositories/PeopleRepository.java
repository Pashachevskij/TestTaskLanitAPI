package ru.akimov.testapilanit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akimov.testapilanit.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
}
