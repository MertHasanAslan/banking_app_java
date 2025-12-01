package com.banking.banking_app_java.service;

import com.banking.banking_app_java.dto.BankResponse;
import com.banking.banking_app_java.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);

}
