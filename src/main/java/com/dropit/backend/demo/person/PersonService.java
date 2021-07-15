package com.dropit.backend.demo.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person getSinglePerson(Long id) {
        Optional<Person> checkPerson = personRepository.findById(id);
        if(checkPerson.isEmpty()) {
            throw new IllegalStateException("Person with id " + id + " does not exist!");
        }
        return personRepository.getById(id);
    }

    public void addNewPerson(Person person) {
        Optional<Person> checkPerson = personRepository.findPersonByEmail(person.getEmail());
        if(checkPerson.isPresent()) {
            throw new IllegalStateException("Email is already assigned to another account!");
        }
        personRepository.save(person);
    }

    public void deletePerson(Long personId) {
        boolean exists = personRepository.existsById(personId);
        if(!exists) {
            throw new IllegalStateException("Person with id " + personId + " does not exist!");
        }
        personRepository.deleteById(personId);
    }

    @Transactional
    public void updatePerson(Long personId, String name, String email, String country) {
        Person person = personRepository.findById(personId).orElseThrow(() -> new IllegalStateException("Person with id " + personId + " does not exist!"));

        if(name != null && name.length() > 0 && !Objects.equals(person.getName(), name)) {
            person.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(person.getEmail(), email)) {
            Optional<Person> checkPerson = personRepository.findPersonByEmail(person.getEmail());
            if(checkPerson.isPresent()) {
                throw new IllegalStateException("Email is already assigned to another account!");
            }
            person.setEmail(email);
        }

        if(country != null && country.length() > 0 && !Objects.equals(person.getCountry(), country)) {
            person.setCountry(country);
        }
    }
}
