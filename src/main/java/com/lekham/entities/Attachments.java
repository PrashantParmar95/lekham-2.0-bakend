package com.lekham.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "attachments")
public class Attachments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String fileUrl;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "lekh_id")
    private long lekhId;
}
