package com.lekham.communication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.security.SecureRandom;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a 6-digit OTP to the given email address.
     * Returns the OTP so caller can store/verify as needed.
     */
    public String sendOtp(String toEmail) {
        // Generate a 6-digit numeric OTP
        SecureRandom rand = new SecureRandom();
        int num = rand.nextInt(1_000_000);           // 0..999999
        String otp = String.format("%06d", num);     // zero-padded to 6 digits

        // Build a simple HTML with the OTP shown large & bold
        String subject = "Lekham - OTP";
        String html = """
                <div style="font-family:Arial,Helvetica,sans-serif;line-height:1.6;">
        
                  <p style="margin:0 0 14px;">Use the OTP below to proceed. It expires in 2 minutes.</p>
                  <div style="margin:12px 0;">
                    <span style="display:inline-block;font-size:40px;font-weight:800;letter-spacing:6px;">%s</span>
                  </div>
                   <hr style="border:none;border-top:1px solid #eee;margin:24px 0;">
                
                        <div style="margin-top:12px;">
                          <p style="margin:0;font-size:15px;color:#444;">Thanks,</p>
                          <p style="margin:4px 0 0;font-size:17px;font-weight:bold;color:#2d89ef;">
                            Team Lekham
                          </p>
                        </div>
                </div>
                """.formatted(otp);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(html, true); // true = HTML

            mailSender.send(message);
            return otp;
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send OTP email: " + e.getMessage(), e);
        }
    }
}
