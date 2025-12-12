package com.banking.banking_app_java.utils;

import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This email has already linked with an account!";



    public static String generateAccountNumber(){
        Year currentYear = Year.now();

        int min = 100000;
        int max = 999999;

        int randNum = (int)Math.floor(Math.random() * (max-min+1) + min);

        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNum);

        String accountNumber = year + randomNumber;
        return accountNumber;
    }

}
