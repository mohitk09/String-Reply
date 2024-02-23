package com.beta.replyservice;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RuleProcessor {
    public static String processRule(String rule, String input) {
        if (!isValidRule(rule)) {
            throw new IllegalArgumentException("Invalid rule");
        }

        char[] operations = rule.toCharArray();
        String result = input;

        for (char operation : operations) {
            switch (operation) {
                case '1':
                    result = reverseString(result);
                    break;
                case '2':
                    result = hashString(result);
                    break;
                // Add more cases for future rules
                default:
                    throw new IllegalArgumentException("Invalid rule operation");
            }
        }

        return result;
    }

    private static boolean isValidRule(String rule) {
        // Validate that the rule only contains valid operations (1 and 2)
        return rule.matches("[12]+");
    }

    private static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    private static String hashString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());

            // Convert bytes to hex
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte hashByte : hashBytes) {
                hexStringBuilder.append(String.format("%02x", hashByte));
            }

            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available");
        }
    }
}
