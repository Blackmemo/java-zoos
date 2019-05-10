package com.javazoos.javazoos.repos;

import com.javazoos.javazoos.models.Zoo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ZooRepository extends CrudRepository<Zoo, Long>
{
    @Query(value = "SELECT * FROM zoo WHERE zoo.zooname = :name", nativeQuery = true)
    Zoo findZooByName(@Param("name") String name);

    @Modifying
    @Query(value = "DELETE FROM animalzoos WHERE zooid = :zooid", nativeQuery = true)
    void deleteZooFromAnimalZoos(long zooid);
}
