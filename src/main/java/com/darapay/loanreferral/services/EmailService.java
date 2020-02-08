package com.darapay.loanreferral.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public interface EmailService {
    void sendSimpleMessage(String from,
                           String to,
                           String subject,
                           String text);
    void sendSimpleMessageUsingTemplate(String from,
                                        String to,
                                        String subject,
                                        SimpleMailMessage template,
                                        String... templateArgs);
    void sendMessageWithAttachment(String from,
                                   String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);

    void sendMessageWithHtmlTemplate(String from,
                                     String[] to,
                                     String subject,
                                     String cc,
                                     String htmltext);
}
