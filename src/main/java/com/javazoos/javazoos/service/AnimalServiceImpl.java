package com.javazoos.javazoos.service;

import com.javazoos.javazoos.models.Animal;
import com.javazoos.javazoos.repos.AnimalRepository;
import com.javazoos.javazoos.view.CountZoosForAnimals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "animalService")
public class AnimalServiceImpl implements AnimalService
{
    @Autowired
    private AnimalRepository animalRepos;

    @Override
    public ArrayList<Animal> findAll()
    {
        ArrayList<Animal> list = new ArrayList<>();
        animalRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public ArrayList<CountZoosForAnimals> getCountZoosForAnimals()
    {
        return animalRepos.getCountZoosForAnimals();
    }

    @Override
    public Animal findAnimalByName(String name)
    {
        return animalRepos.findAnimalByName(name);
    }
}
