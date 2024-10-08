package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Mail sunucusu ayarları
        mailSender.setHost("smtp.example.com"); // SMTP sunucusunun adresini buraya ekleyin
        mailSender.setPort(587); // SMTP portunu ayarlayın (genellikle 587 veya 465)

        mailSender.setUsername("projelermaili@gmail.com"); // SMTP kullanıcı adınızı buraya ekleyin
        mailSender.setPassword("12345678"); // SMTP şifrenizi buraya ekleyin

        // E-posta ayarları
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.debug", "true");

                return mailSender;
                }
                }