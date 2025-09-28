package com.lekham.entities;

import com.lekham.enums.OtpType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "otp_record")
public class OtpRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String mobile;
    @Column(name = "otp_type")
    private OtpType otpType;
    private String otp;
    private Date time;
}
