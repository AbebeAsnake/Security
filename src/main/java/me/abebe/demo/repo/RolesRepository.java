package me.abebe.demo.repo;

import me.abebe.demo.model.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Roles,Long> {
    Roles findRolesByRoleName(String roleName);

}
