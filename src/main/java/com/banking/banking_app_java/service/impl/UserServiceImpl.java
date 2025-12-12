package com.banking.banking_app_java.service.impl;

import com.banking.banking_app_java.dto.AccountInfo;
import com.banking.banking_app_java.dto.BankResponse;
import com.banking.banking_app_java.dto.UserRequest;
import com.banking.banking_app_java.entity.User;
import com.banking.banking_app_java.repository.UserRepository;
import com.banking.banking_app_java.service.UserService;
import com.banking.banking_app_java.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public BankResponse createAccount(UserRequest userRequest) { // Create a new user and save it into db

        if(userRepository.existsByEmail(userRequest.getEmail())){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .middleName(userRequest.getMiddleName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .secondPhoneNumber(userRequest.getSecondPhoneNumber())
                .status("ACTIVE")
                .build();

        userRepository.save(newUser);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(newUser.getFirstName() + " " + newUser.getLastName())
                        .accountBalance(newUser.getAccountBalance())
                        .accountNumber(newUser.getAccountNumber())
                        .build())
                .build();

    }
}
