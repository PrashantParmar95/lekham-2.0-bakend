package com.lekham.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "lekh_share")
public class LekhShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="lekh_id")
    private long lekhId;
    @Column(name="user_id")
    private long userId;
    private boolean accepted;
}
