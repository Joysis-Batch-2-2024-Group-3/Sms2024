package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {

    // Method to hash a password using SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder hashString = new StringBuilder();

            for (byte b : hashBytes) {
                // Converts each byte to a hexadecimal string
                hashString.append(String.format("%02x", b));
            }
            return hashString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Method to verify if a plain password matches a hashed password
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        // Hash the plain password
        String hashedPlainPassword = hashPassword(plainPassword);

        // Compare the newly hashed password with the stored hash
        return hashedPlainPassword.equals(hashedPassword);
    }


}
