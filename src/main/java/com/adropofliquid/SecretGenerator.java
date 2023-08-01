package com.adropofliquid;

import org.web3j.crypto.*;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

public class SecretGenerator {

    public static void main(String[] args) throws CipherException, IOException {
//
//        Bip39Wallet wallet = generate("pass");
//        System.out.println(wallet.getMnemonic());
//        System.out.println(wallet.getFilename());
//        SecureRandom secureRandom = new SecureRandom();
//        byte[] initialEntropy = new byte[16];
//        secureRandom.nextBytes(initialEntropy);
//        String mnemonic = MnemonicUtils.generateMnemonic(initialEntropy);
//        System.out.println(mnemonic);
//        String mnemonic = MnemonicUtils.generateMnemonic(new SecureRandom());

//        Credentials credentials = WalletUtils.loadBip39Credentials(null, "wink latin embody main system hint announce job tomato view fade provide");
        Credentials credentials = WalletUtils.loadBip39Credentials("pass", "merge relief design drift squirrel excess reform surface spirit until trend ball");
        System.out.println(credentials.getAddress());
        System.out.println(credentials.getEcKeyPair().getPublicKey().toString(16));
        System.out.println(credentials.getEcKeyPair().getPrivateKey().toString(16));
    }

    private static Bip39Wallet generate(String walletPassword) throws CipherException, IOException {
        File walletDirectory = new File(Constants.PATH);
        Bip39Wallet walletName = WalletUtils.generateBip39Wallet(walletPassword, walletDirectory);
        return walletName;
    }
}