package com.javazoos.javazoos.service;

import com.javazoos.javazoos.repos.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "telephoneService")
public class TelephoneServiceImpl implements TelephoneService
{
    @Autowired
    private TelephoneRepository telephoneRepos;
}
