package com.lekham.repositories;


import com.lekham.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query(value = "SELECT COUNT(id) FROM roles WHERE role_name = :roleName", nativeQuery = true)
    int getRoleCountByRoleName(@Param("roleName") String roleName);

}
