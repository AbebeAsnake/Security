package me.abebe.demo.repo;

import me.abebe.demo.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users,Long>{
    Users findUsersByUserName(String username);
}
