package com.lekham.entities.audit;
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
@Table(name = "lekh_audit")
public class LekhAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "lekh_id")
    private long lekhId;
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
    
}


