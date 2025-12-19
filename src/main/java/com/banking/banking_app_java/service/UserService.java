package com.banking.banking_app_java.service;

import com.banking.banking_app_java.dto.BankResponse;
import com.banking.banking_app_java.dto.CreditDebitRequest;
import com.banking.banking_app_java.dto.EnquiryRequest;
import com.banking.banking_app_java.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);
    String nameEnquiry(EnquiryRequest enquiryRequest);
    BankResponse creditAccount(CreditDebitRequest creditDebitRequest);
}
