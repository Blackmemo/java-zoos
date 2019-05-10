package com.javazoos.javazoos.repos;

import com.javazoos.javazoos.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}