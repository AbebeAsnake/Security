package me.abebe.demo.setup;

import me.abebe.demo.model.Roles;
import me.abebe.demo.repo.RolesRepository;
import me.abebe.demo.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RolesRepository roleRepo;
    @Autowired
    UsersRepository userRepo;
    @Override
public void run(String... strings){
        Roles role = new Roles();
        role.setRoleName("EMPLOYER");
        roleRepo.save(role);

        role = new Roles();
        role.setRoleName("APPLICANT");
        roleRepo.save(role);

        role = new Roles();
        role.setRoleName("ADMIN");
        roleRepo.save(role);


    }

}
