package com.banking.banking_app_java.service.impl;

import com.banking.banking_app_java.dto.BankResponse;
import com.banking.banking_app_java.dto.UserRequest;
import com.banking.banking_app_java.entity.User;
import com.banking.banking_app_java.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public BankResponse createAccount(UserRequest userRequest) { // Create a new user and save it into db
        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .middleName(userRequest.getMiddleName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNUmber()
    }
}
