package com.adropofliquid;

import com.tiemens.secretshare.engine.SecretShare;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class Presentation {

    public void mainMenu(){

    }
    public static void main(String[] args) throws UnsupportedEncodingException {
//
//        for(String s : splitSecretIntoPieces("secret", 3,2))
//            System.out.println(s);

        System.out.println(mergePiecesIntoSecret(new String[]{"3:2:1:126879297332611:68562432350290","3:2:2:126879297332611:10245567367984"}));
    }

    static String[] splitSecretIntoPieces(String secret, int n, int k) throws UnsupportedEncodingException {
        BigInteger secretInteger = stringToBigInteger(secret);
        BigInteger modulus = secretInteger.nextProbablePrime();
        SecretShare.PublicInfo publicInfo = new SecretShare.PublicInfo(n, k, modulus, (String)null);
        SecretShare.SplitSecretOutput splitSecretOutput = (new SecretShare(publicInfo)).split(secretInteger);
        List<SecretShare.ShareInfo> pieces = splitSecretOutput.getShareInfos();
        String[] out = new String[pieces.size()];

        for(int i = 0; i < out.length; ++i) {
            SecretShare.ShareInfo piece = (SecretShare.ShareInfo)pieces.get(i);
            out[i] = n + ":" + k + ":" + piece.getX() + ":" + publicInfo.getPrimeModulus() + ":" + piece.getShare();
        }

        return out;
    }
    static BigInteger stringToBigInteger(String in) throws UnsupportedEncodingException {
        return new BigInteger(in.getBytes(StandardCharsets.UTF_8));
    }

    static String mergePiecesIntoSecret(String[] pieces) throws UnsupportedEncodingException {
        SecretShare.ShareInfo shareInfo = newShareInfo(pieces[0]);
        SecretShare.PublicInfo publicInfo = shareInfo.getPublicInfo();
        SecretShare solver = new SecretShare(publicInfo);
        int k = publicInfo.getK();
        List<SecretShare.ShareInfo> kPieces = new ArrayList<>(k);
        kPieces.add(shareInfo);

        for(int i = 1; i < pieces.length && i < k; ++i) {
            kPieces.add(newShareInfo(pieces[i]));
        }

        SecretShare.CombineOutput solved = solver.combine(kPieces);
        BigInteger secret = solved.getSecret();
        return new String(secret.toByteArray(), StandardCharsets.UTF_8);
    }

    private static SecretShare.ShareInfo newShareInfo(String piece) {
        String[] parts = piece.split(":");
        int i = 0;
        int n = Integer.parseInt(parts[i++]);
        int k = Integer.parseInt(parts[i++]);
        int x = Integer.parseInt(parts[i++]);
        BigInteger primeModulus = new BigInteger(parts[i++]);
        BigInteger share = new BigInteger(parts[i++]);
        if (!piece.equals("" + n + ":" + k + ":" + x + ":" + primeModulus + ":" + share)) {
            throw new RuntimeException("Failed to parse " + piece);
        } else {
            return new SecretShare.ShareInfo(x, share, new SecretShare.PublicInfo(n, k, primeModulus, (String)null));
        }
    }

//3:2:1:126879297332611:68562432350290
}
