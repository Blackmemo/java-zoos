package com.javazoos.javazoos;

import com.javazoos.javazoos.models.Role;
import com.javazoos.javazoos.models.User;
import com.javazoos.javazoos.models.UserRoles;
import com.javazoos.javazoos.repos.RoleRepository;
import com.javazoos.javazoos.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("zoodata");
        Role r4 = new Role("animaldata");
        Role r5 = new Role("mgr");

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        admins.add(new UserRoles(new User(), r4));
        admins.add(new UserRoles(new User(), r5));

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> zoodata = new ArrayList<>();
        zoodata.add(new UserRoles(new User(), r3));

        ArrayList<UserRoles> animaldata = new ArrayList<>();
        animaldata.add(new UserRoles(new User(), r4));

        ArrayList<UserRoles> mgr = new ArrayList<>();
        mgr.add(new UserRoles(new User(), r5));

        rolerepos.save(r1);
        rolerepos.save(r2);
        rolerepos.save(r3);
        rolerepos.save(r4);
        rolerepos.save(r5);

        User u1 = new User("User", "password", users);

        User u2 = new User("admin", "password", admins);

        User u3 = new User("zoodata", "password", zoodata);

        User u4 = new User("animaldata", "password", animaldata);

        User u5 = new User("mgr", "password", mgr);

        userrepos.save(u1);
        userrepos.save(u2);
        userrepos.save(u3);
        userrepos.save(u4);
        userrepos.save(u5);
    }
}
