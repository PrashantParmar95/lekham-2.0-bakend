package com.lekham.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @Column(name = "created_by")
    private long createdBy;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "active")
    private boolean isActive;
    @Column(name = "deactivated")
    private Date deactivatedDate;
}
