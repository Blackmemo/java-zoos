package com.javazoos.javazoos.service;

import com.javazoos.javazoos.models.Animal;
import com.javazoos.javazoos.models.Zoo;
import com.javazoos.javazoos.repos.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "zooService")
public class ZooServiceImpl implements ZooService
{
    @Autowired
    private ZooRepository zooRepos;

    @Override
    public ArrayList<Zoo> findAll()
    {
        ArrayList<Zoo> list = new ArrayList<>();
        zooRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Zoo findZooByName(String name)
    {
       return zooRepos.findZooByName(name);
    }

    @Transactional
    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (zooRepos.findById(id).isPresent())
        {
            zooRepos.deleteZooFromAnimalZoos(id);
            zooRepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }

    }

    @Transactional
    @Override
    public Zoo save(Zoo zoo)
    {
        Zoo newZoo = new Zoo();

        newZoo.setZooname(zoo.getZooname());
        newZoo.setAnimals(zoo.getAnimals());
        newZoo.setTelephones(zoo.getTelephones());
        newZoo.setZooid(zoo.getZooid());

        return zooRepos.save(newZoo);
    }

    @Transactional
    @Override
    public Zoo update(Zoo zoo, long zooid)
    {
        Zoo currentZoo = zooRepos.findById(zooid)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(zooid)));

        if (zoo.getZooname() != null)
        {
            currentZoo.setZooname(zoo.getZooname());
        }

        if (zoo.getTelephones() != null)
        {
            currentZoo.setTelephones(zoo.getTelephones());
        }

    return zooRepos.save(currentZoo);
    }
}
