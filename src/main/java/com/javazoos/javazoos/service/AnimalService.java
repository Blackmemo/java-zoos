package com.javazoos.javazoos.service;

import com.javazoos.javazoos.models.Animal;
import com.javazoos.javazoos.view.CountZoosForAnimals;

import java.util.ArrayList;

public interface AnimalService
{
    ArrayList<Animal> findAll();

    ArrayList<CountZoosForAnimals> getCountZoosForAnimals();

    Animal findAnimalByName(String name);
}
