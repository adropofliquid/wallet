package com.adropofliquid;

import org.web3j.crypto.*;

import java.security.SecureRandom;

public class SeedPhraseBip39 {

    public static final int TWELVE = 16;
    public static final int TWENTY_FOUR = 32;

    public static String generateSeedPhrase(int byteArrayLength){
        SecureRandom secureRandom = new SecureRandom(); //
        byte[] initialEntropy = new byte[byteArrayLength];
        secureRandom.nextBytes(initialEntropy);

        return MnemonicUtils.generateMnemonic(initialEntropy);
    }
}