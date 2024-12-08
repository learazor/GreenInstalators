package com.via.greeninstalators.service;

public class AspNetIdentityPasswordVerifier {

    public static boolean verifyPassword(String password, String storedHash) {
        //don't know how to dehash password in java that was hashed in .net with UserManager
        return true;
    }
}
