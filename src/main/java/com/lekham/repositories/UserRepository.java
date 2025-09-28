package com.lekham.repositories;

import com.lekham.entities.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

    @Query(value = "SELECT COUNT(id) FROM users WHERE email = :email", nativeQuery = true)
    public int isUserExistsByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<Users> findByEmail(@Param("email") String email);



    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    Optional<Users> findByUsername(@Param("username") String username);
}
