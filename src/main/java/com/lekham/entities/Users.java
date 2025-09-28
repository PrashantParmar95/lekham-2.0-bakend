package com.lekham.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;


import java.util.Date;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_bio")
    private String userBio;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;
    @Column(unique = true, nullable = false)
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

    @PrePersist
    protected void onCreate() {
        this.createdTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = new Date();
    }
}
