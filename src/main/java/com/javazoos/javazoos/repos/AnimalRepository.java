package com.javazoos.javazoos.repos;

import com.javazoos.javazoos.models.Animal;
import com.javazoos.javazoos.view.CountZoosForAnimals;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface AnimalRepository extends CrudRepository<Animal, Long>
{
    @Query(value = "SELECT a.animalid, animaltype, count(zooid) as countzoos FROM animalzoos a INNER JOIN animal z on a.animalid = z.animalid GROUP BY a.animalid, animaltype", nativeQuery = true)
    ArrayList<CountZoosForAnimals> getCountZoosForAnimals();

    @Query(value = "SELECT * FROM animal WHERE animal.animaltype = :name", nativeQuery = true)
    Animal findAnimalByName(@Param("name") String name);
}
