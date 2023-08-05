package com.adropofliquid;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;

public class WalletExperiment {


    public static void main(String[] args) throws CipherException, IOException {
        experiment();
    }

    private static void experiment() throws CipherException, IOException {

        byte[] bytes = {
                0x23, 0x45, 0x67, 0x49, 0x33, 0x45, 0x21, 0x12,
                0x23, 0x45, 0x67, 0x49, 0x33, 0x45, 0x21, 0x12
        };

        //checking to see if the same Mnemonic is generated everytime with the same entropy
        for (int i = 0; i<500; i++)
            System.out.println(MnemonicUtils.generateMnemonic(bytes));

    }
}
