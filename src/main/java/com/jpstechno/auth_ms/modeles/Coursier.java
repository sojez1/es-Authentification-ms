package com.jpstechno.auth_ms.modeles;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Coursier {

    private final JavaMailSender mailSender;

    public void envoyerEmail(String destinataire, String texteEnHtml, String objet) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(destinataire);
            helper.setSubject(objet);
            helper.setText(texteEnHtml, true); // true indique que le texte est en HTML
            helper.setFrom("EasySchool");
            mailSender.send(message);
        } catch (MessagingException mex) {
            String errorMessage = mex.getMessage();
            throw new RuntimeException(errorMessage);

        }

    }
}
