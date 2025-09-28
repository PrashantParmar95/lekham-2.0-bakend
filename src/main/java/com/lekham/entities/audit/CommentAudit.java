package com.lekham.entities.audit;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "comments_audit")
public class CommentAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "comment_id")
    private long commentId;
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
