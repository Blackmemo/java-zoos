package com.javazoos.javazoos.service;

import com.javazoos.javazoos.models.Zoo;

import java.util.ArrayList;

public interface ZooService
{
    ArrayList<Zoo> findAll();

    Zoo findZooByName(String name);

    void delete(long id);

    Zoo save(Zoo zoo);

    Zoo update(Zoo zoo, long zooid);
}
