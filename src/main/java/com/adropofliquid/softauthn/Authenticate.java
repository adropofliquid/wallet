package com.adropofliquid.softauthn;

import com.yubico.webauthn.data.*;
import de.adesso.softauthn.Authenticator;
import de.adesso.softauthn.Authenticators;
import de.adesso.softauthn.CredentialsContainer;
import de.adesso.softauthn.Origin;
import de.adesso.softauthn.authenticator.WebAuthnAuthenticator;

import java.util.List;

public class Authenticate {

    public static void main(String[] args) {

        // Create an authenticator that will implement the functionality of a WebAuthn authenticator in pure software
// alternatively, you can use one of the templates in the Authenticators class
        Authenticator authenticator = Authenticators.yubikey5Nfc().build();
// Create a credentials container (mimics the browser navigator.credentials API)
// It will pretend its origin is https://example.com (no port, no extra domain)
        Origin origin = new Origin("https", "example.com", -1, null);
        CredentialsContainer credentials = new CredentialsContainer(origin, List.of(authenticator));
// Get the options for credential creation from your backend
        PublicKeyCredentialCreationOptions opts = startRegistration();
        PublicKeyCredential<AuthenticatorAttestationResponse, ClientRegistrationExtensionOutputs> publicKeyCredential = credentials.create(opts);
//        verifyAttestation(publicKeyCredential);

        System.out.println("Nothing");
    }



    private static PublicKeyCredentialCreationOptions startRegistration() {
        try {
            return PublicKeyCredentialCreationOptions.fromJson("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerAssertion(){

    }
}
