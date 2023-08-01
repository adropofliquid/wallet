package com.adropofliquid;

import org.mitre.secretsharing.Part;
import org.mitre.secretsharing.Secrets;
import org.mitre.secretsharing.codec.PartFormats;
import org.mitre.secretsharing.util.InputValidationException;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        mainMenu(sc);
        sc.close();

    }

    public static void mainMenu(Scanner sc){
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("+++++++++++MAIN MENU++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("Select Options");
        System.out.println("1. Save new secret (e.g Keys, Passwords)");
        System.out.println("2. Regenerate secret");
        System.out.println("0. Quit");
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("Option >>");

        int selection = sc.nextInt();
        sc.nextLine();

        switch (selection) {
            case 1 -> saveNewSecret(sc);
            case 2 -> {
                try{regenerateSecret(sc);}
                catch (InputValidationException e){
                    System.out.println(e.getMessage());
                    System.out.println("Press enter to retry........");
                    sc.nextLine();
                }
                finally {
                    regenerateSecret(sc);
                }
            }
            default -> {}
        }
    }
    private static void regenerateSecret(Scanner sc) throws InputValidationException {
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++REGENERATE SECRET+++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("Input your secret shares(seperated with ;) >>");
        String shares = sc.nextLine();

        String[] splitShares = shares.split(";");

        //join
        Part[] p = new Part[splitShares.length];
        for(int i = 0; i < p.length; i++)
            p[i] = PartFormats.parse(splitShares[i]);


        byte[] ss = Secrets.join(p);
        String secretString = new String(ss, StandardCharsets.UTF_8);

        System.out.println("++++++++++++++++++++++++++++++++++");

        System.out.println("Your secret is: " + secretString);


        System.out.println("Press enter to continue.....");
        sc.nextLine();
        mainMenu(sc);
    }
    private static void saveNewSecret(Scanner sc) {
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++SAVE SECRET+++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("Input your secret >>");

        String secret = sc.nextLine();

        System.out.println("Parties and Threshold? (p,t) >>");

        String[] threshold = sc.nextLine().split(",");

        //perform split
        Part[] parts = Secrets.split(secret.getBytes(StandardCharsets.UTF_8),
                Integer.parseInt(threshold[0]),
                Integer.parseInt(threshold[1]),
                new SecureRandom());

        //print shares
        System.out.println("Your shares below >>>>");
        for(Part p : parts)
            System.out.println(PartFormats.currentStringFormat().format(p));

        System.out.println("Press enter to continue....");
        sc.nextLine();

        mainMenu(sc);
    }
}