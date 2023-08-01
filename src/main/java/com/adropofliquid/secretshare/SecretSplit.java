package com.adropofliquid.secretshare;

import com.adropofliquid.secretshare.shamir.Scheme;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import static java.nio.charset.StandardCharsets.UTF_8;

public class SecretSplit {


    public static String[] splitSecretBase64(String secret, int parties, int threshold){
        String [] parts = new String[parties];
        Map<Integer, byte[]> split = splitSecret(secret, parties, threshold);

        for(int i = 0; i < parts.length; i++)
            parts[i] = Base64.getEncoder().encodeToString(split.get(i+1));

        return parts;
    }

    public static Map<Integer,byte[]> splitSecret(String secret, int parties, int threshold) {

        Scheme mainScheme = new Scheme(new SecureRandom(), parties, threshold);
        return mainScheme.split(secret.getBytes(UTF_8));
    }

    public static byte[] join(Map<Integer, byte[]> parts) {
        return Scheme.join(parts);
    }

    public static String getSecret(String[] shares) {
        Map<Integer, byte[]> parts = new HashMap<>();

        for(int i = 0; i < shares.length; i++){
            parts.put(i+1, Base64.getDecoder().decode(shares[i]));
        }
        byte[] fullSecret = join(parts);


        return new String(fullSecret, StandardCharsets.UTF_8);
    }

}
