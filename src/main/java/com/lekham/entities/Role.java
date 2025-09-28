package com.lekham.entities;


import com.lekham.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, name = "role_name", nullable = false, length = 100)
    private Roles roleName;

    @Column(name = "role_description")
    private String roleDescription;

}
