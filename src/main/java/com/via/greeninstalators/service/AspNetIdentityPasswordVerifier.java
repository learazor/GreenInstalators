package com.via.greeninstalators.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class AspNetIdentityPasswordVerifier {

    public static boolean verifyPassword(String password, String storedHash) {
        if (password == null || storedHash == null || storedHash.isEmpty()) {
            return false;
        }
        try {
            // Parse stored hash
            String[] parts = storedHash.split("\\$");
            if (parts.length != 4) {
                return false;
            }

            int iterations = Integer.parseInt(parts[1]);
            byte[] salt = Base64.getDecoder().decode(parts[2]);
            byte[] storedHashBytes = Base64.getDecoder().decode(parts[3]);

            // Hash the input password
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, storedHashBytes.length * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] testHash = skf.generateSecret(spec).getEncoded();

            // Compare the hashes in constant time
            return Arrays.equals(storedHashBytes, testHash);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}