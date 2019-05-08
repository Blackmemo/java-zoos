package com.javazoos.javazoos.controllers;

import com.javazoos.javazoos.models.Zoo;
import com.javazoos.javazoos.service.AnimalService;
import com.javazoos.javazoos.service.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/project")
public class ZooController
{
    @Autowired
    private AnimalService animalService;

    @Autowired
    private ZooService zooService;

    @GetMapping(value = "/zoos/zoos", produces = {"application/json"})
    public ResponseEntity<?> listAllZoos()
    {
        return new ResponseEntity<>(zooService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/animals/animals", produces = {"application/json"})
    public ResponseEntity<?> listAllAnimals()
    {
        return new ResponseEntity<>(animalService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/animals/count", produces = {"application/json"})
    public ResponseEntity<?> getCountZoosForAnimals()
    {
        return new ResponseEntity<>(animalService.getCountZoosForAnimals(), HttpStatus.OK);
    }

    @GetMapping(value = "/zoos/{name}", produces = {"application/json"})
    public ResponseEntity<?> getZooByName(@PathVariable String name)
    {
       return new ResponseEntity<>(zooService.findZooByName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/animals/{name}", produces = {"application/json"})
    public ResponseEntity<?> getAnimalByName(@PathVariable String name)
    {
        return new ResponseEntity<>(animalService.findAnimalByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/admin/zoos/{id}")
    public ResponseEntity<?> deleteZooById(@PathVariable long zooid)
    {
        zooService.delete(zooid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/admin/zoos",
    consumes = {"application/json"},
    produces = {"application/json"})
    public ResponseEntity<?> addNewZoo(@Valid
                                       @RequestBody
                                            Zoo newZoo) throws URISyntaxException
    {
        newZoo = zooService.save(newZoo);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{zooid}").buildAndExpand(newZoo.getZooid()).toUri();
        responseHeaders.setLocation(newRestaurantURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/admin/zoos/{id}")
    public ResponseEntity<?> updateZoo(
            @RequestBody
                Zoo updateZoo,
            @PathVariable
            long zooid)
    {
        zooService.update(updateZoo, zooid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
