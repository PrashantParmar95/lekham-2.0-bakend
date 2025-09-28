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
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String comment;
    private int level;
    @Column(name = "parent_id")
    private long parentId;
    @Column(name = "lekh_id")
    private long lekhId;
    private boolean deleted;
    @Column(name = "created_by")
    private long createdBy;
    @Column(name = "created_time")
    private Date createTime;


}
