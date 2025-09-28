package com.lekham.entities;

import com.lekham.enums.LekhAccess;
import com.lekham.enums.LekhPriority;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "lekh")
public class Lekh {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Column(name = "content_id")
    private String contentId;
    @Column(name = "category_id")
    private long categoryId;
    private boolean published;
    private boolean deleted;
    private LekhAccess access;
    private LekhPriority priority;
    private String bgc;
    private String txtc;
    @Column(name = "parent_id")
    private long parentId;
    @Column(name = "created_time")
    private Date createdTime;
    @Column(name = "updated_time")
    private Date updatedTime;
    @Column(name = "created_by")
    private long createdBy;
    @Column(name = "updated_by")
    private long updatedBy;

    @PrePersist
    protected void onCreate() {
        this.createdTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = new Date();
    }


}
