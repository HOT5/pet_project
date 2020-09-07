package com.epam.rd.util;

import org.mindrot.jbcrypt.BCrypt;

public class HashPassword {
    private static int rounds = 11;

    public static String hashPassword(String plaintext) {
        String salt = BCrypt.gensalt(rounds);
        return (BCrypt.hashpw(plaintext, salt));
    }

    public static boolean checkPassword(String password, String hash) {
        if (hash == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(password, hash);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
