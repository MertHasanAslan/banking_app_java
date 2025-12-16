package com.banking.banking_app_java.service;


import com.banking.banking_app_java.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
