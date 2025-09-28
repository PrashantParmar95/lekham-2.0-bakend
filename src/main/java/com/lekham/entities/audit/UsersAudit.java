package com.lekham.entities.audit;


import com.lekham.entities.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "users_audit")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "user_bio")
    private String userBio;
    private String email;
    private String username;
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "role_id")
    private long roleId;
    @Column(name = "created_time")
    private Date createdTime;
    @Column(name = "updated_time")
    private Date updatedTime;
    @Column(name = "created_by")
    private long createdBy;
    @Column(name = "updated_by")
    private long updatedBy;



    public static UsersAudit fromUser(Users user) {
        UsersAudit audit = new UsersAudit();
        audit.setUserId(user.getId());
        audit.setUserBio(user.getUserBio());
        audit.setEmail(user.getUsername());
        audit.setUsername(user.getUsername());
        audit.setPassword(user.getPassword());
        audit.setPhoneNumber(user.getPhoneNumber());
        audit.setRoleId(user.getRoleId());
        audit.setCreatedTime(user.getCreatedTime());
        audit.setUpdatedTime(user.getUpdatedTime());
        audit.setCreatedBy(user.getCreatedBy());
        audit.setUpdatedBy(user.getUpdatedBy());

        return audit;
    }

}

