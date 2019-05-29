package com.happytown.core.use_cases;

import javax.mail.MessagingException;

public interface NotificationProvider {

    void notifier(String to, String subject, String body) throws MessagingException;
}
