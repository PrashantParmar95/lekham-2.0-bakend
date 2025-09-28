package com.lekham.controller;

import com.lekham.entities.Role;
import com.lekham.enums.Roles;
import com.lekham.services.RoleService;
import com.mongodb.annotations.Sealed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }
}

