package com.example.weathertracker.Helper;

public  class InputValidator {
    public static boolean  isValidPinCode(String pincode){
        return pincode != null && pincode.matches("^[1-9][0-9]{5}$");
    }
}
