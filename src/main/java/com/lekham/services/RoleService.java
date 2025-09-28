package com.lekham.services;


import com.lekham.entities.Role;
import com.lekham.enums.Roles;
import com.lekham.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<Role> addRole(Role role) {

        int count = roleRepository.getRoleCountByRoleName(role.getRoleName().toString());

        if (count > 0) {
            throw new RuntimeException("Role already exists");
        }
        Role savedRole = roleRepository.save(role);

        return new ResponseEntity<>(savedRole, HttpStatus.OK);

    }
}
