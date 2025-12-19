package com.banking.banking_app_java.service.impl;

import com.banking.banking_app_java.dto.*;
import com.banking.banking_app_java.entity.User;
import com.banking.banking_app_java.repository.UserRepository;
import com.banking.banking_app_java.service.EmailService;
import com.banking.banking_app_java.service.UserService;
import com.banking.banking_app_java.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.banking.banking_app_java.utils.AccountUtils.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

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
        // Sending email after creating the
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(newUser.getEmail())
                .subject("Account Has Created!")
                .messageBody("Congratulations!, your account has been created.")
                .build();

        emailService.sendEmailAlert(emailDetails);

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

    // balance, name enquiry and credit, debit, transfer
    @Override
    public BankResponse balanceEnquiry(EnquiryRequest enquiryRequest){
        // Checking if the account number exists
        boolean isAccountNumExist = userRepository.existsByAccountNumber(enquiryRequest.getAccountNumber());

        if (!isAccountNumExist){
            return BankResponse.builder()
                    .responseCode(ACCOUNT_NOT_EXISTS_CODE)
                    .responseMessage(ACCOUNT_NOT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User foundUser = userRepository.findByAccountNumber(enquiryRequest.getAccountNumber());

        return BankResponse.builder()
                .responseCode(ACCOUNT_FOUND_CODE)
                .responseMessage(ACCOUNT_FOUND_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(foundUser.getFirstName() + " " + foundUser.getMiddleName() + " " + foundUser.getLastName())
                        .accountNumber(foundUser.getAccountNumber())
                        .accountBalance(foundUser.getAccountBalance())
                        .build())
                .build();
    }

    @Override
    public String nameEnquiry(EnquiryRequest enquiryRequest){
        // checking if the account exists
        boolean isAccountNumExists = userRepository.existsByAccountNumber(enquiryRequest.getAccountNumber());
        if (!isAccountNumExists){
            return ACCOUNT_NOT_EXISTS_MESSAGE;
        }

        User foundUser = userRepository.findByAccountNumber(enquiryRequest.getAccountNumber());
        return foundUser.getFirstName() + " " + foundUser.getMiddleName() + " " + foundUser.getLastName();
    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest creditDebitRequest) {
        // checking if the account exists
        boolean isAccountExist = userRepository.existsByAccountNumber(creditDebitRequest.getAccountNumber());
        if(!isAccountExist){
            return BankResponse.builder()
                    .responseCode(ACCOUNT_NOT_EXISTS_CODE)
                    .responseMessage(ACCOUNT_NOT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User foundUser = userRepository.findByAccountNumber(creditDebitRequest.getAccountNumber());

        return BankResponse.builder()
                .responseCode(ACCOUNT_FOUND_CODE)
                .responseMessage(ACCOUNT_FOUND_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(foundUser.getFirstName() + " " + foundUser.getMiddleName() + " " + foundUser.getLastName())
                        .accountNumber(foundUser.getAccountNumber())
                        .accountBalance(foundUser.getAccountBalance())
                        .build())
                .build();
    }
}
