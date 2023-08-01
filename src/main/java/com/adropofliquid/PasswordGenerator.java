package com.adropofliquid;

public class PasswordGenerator {

    public static String insertAtRandom(String str, String toInsert){
        int n = str.length();
        int r = (int)  ((n-1) * Math.random());
        return str.substring(0,r) + toInsert + str.substring(r);
    }

    public static String randomCharacter(String characters){
        int n = characters.length();
        int r = (int) (n*Math.random()); //math random returns real number between 0.0 and 1.0
        return characters.substring(r, r+1);
    }

    public static String makePassword(int length){
        StringBuilder password = new StringBuilder();

        for(int i=0;i<length;i++)
        {
            password.append(randomCharacter("ABCDEFGHIJKLMNOPQRSTUVXYZabcdefghijklmnopqrstuvwxyz"));
        }
        for(int i=0;i<length/3;i++) {
            String randomDigit = randomCharacter("0123456789");
            password = new StringBuilder(insertAtRandom(password.toString(), randomDigit));
            String randomSymbol = randomCharacter("!@#$%^&*()_+[]'/.,><~/*-+");
            password = new StringBuilder(insertAtRandom(password.toString(), randomSymbol));
        }
        return password.substring(0,length);
    }

    public static String generatePass(){
        return makePassword(12);
    }

}
