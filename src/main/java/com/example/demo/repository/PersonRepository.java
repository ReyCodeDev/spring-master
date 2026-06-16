package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();

    @Query(value = "SELECT p from Person p JOIN p.childrenRelations pcr where pcr.child.id = :childId")
    List<Person> findParentsByChildId(Integer childId);
}
