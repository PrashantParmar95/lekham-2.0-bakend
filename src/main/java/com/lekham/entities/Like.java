package com.lekham.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "likes")
public class Like {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long lekhId;
    private boolean deleted;
    @Column(name = "like_by")
    private long likeBy;
}
