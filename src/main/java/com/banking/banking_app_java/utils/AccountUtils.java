package com.banking.banking_app_java.utils;

import java.time.Year;

public class AccountUtils {
    Year currentYear = Year.now();

    int min = 100000;
    int max = 999999;

    int randNum = (int)Math.floor(Math.random() * (max-min+1) + min);

}
