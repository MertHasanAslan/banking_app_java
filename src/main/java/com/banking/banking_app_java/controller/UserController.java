package com.banking.banking_app_java.controller;

import com.banking.banking_app_java.dto.BankResponse;
import com.banking.banking_app_java.dto.EnquiryRequest;
import com.banking.banking_app_java.dto.UserRequest;
import com.banking.banking_app_java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.createAccount(userRequest);
    }

    @PostMapping("/balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.balanceEnquiry(enquiryRequest);
    }

    @PostMapping("/nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.nameEnquiry(enquiryRequest);
    }
}
