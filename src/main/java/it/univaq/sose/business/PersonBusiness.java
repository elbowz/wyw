package it.univaq.sose.business;

import it.univaq.sose.model.Person;
import it.univaq.sose.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonBusiness {

    @Autowired
    PersonRepository repository;

    public PersonBusiness(){}

    public void add(Person test) {
        repository.save(test);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Person> getTests() {
        return (List<Person>) repository.findAll();
    }

    public Person getPersonById(long id) {
        try {
            Optional<Person> optional = repository.findById(id);
            return optional.orElse(null);
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

//    private Dog toEntity(DogDto dto) {
//        Dog entity = new Dog();
//        entity.setName(dto.getName());
//        entity.setAge(dto.getAge());
//        return entity;
//    }
}