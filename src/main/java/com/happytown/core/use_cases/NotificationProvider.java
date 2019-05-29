package com.happytown.core.use_cases;

import com.happytown.dataproviders.mail.NotificationException;

public interface NotificationProvider {

    void notifier(String to, String subject, String body) throws NotificationException;
}
