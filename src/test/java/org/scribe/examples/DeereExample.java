package org.scribe.examples;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.DeereApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import java.util.Scanner;

public class DeereExample
{
  private static final String NETWORK_NAME = "Deere";
  private static final String PROTECTED_RESOURCE_URL = "https://api.deere.com/platform/";

  public static void main(String[] args)
  {
    OAuthService service = new ServiceBuilder()
                                  .provider(DeereApi.class)
                                  .apiKey("johndeere-j9qZ7U5DosANR7Erw8xNS1Sw")
                                  .apiSecret("1b2325874e0ba0c4f31cdde96deadf751ff61f36")
                                  .build();
    Scanner in = new Scanner(System.in);

    System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
    System.out.println();

    // Obtain the Request Token
    System.out.println("Fetching the Request Token...");
    Token requestToken = service.getRequestToken();
    System.out.println("Got the Request Token!");
    System.out.println("(if your curious it looks like this: " + requestToken + " )");
    System.out.println();

    System.out.println("Now go and authorize Scribe here:");
    System.out.println(service.getAuthorizationUrl(requestToken));
    System.out.println("And paste the verifier here");
    System.out.print(">>");
    Verifier verifier = new Verifier(in.nextLine());
    System.out.println();

    // Trade the Request Token and Verfier for the Access Token
    System.out.println("Trading the Request Token for an Access Token...");
    Token accessToken = service.getAccessToken(requestToken, verifier);
    System.out.println("Got the Access Token!");
    System.out.println("(if your curious it looks like this: " + accessToken + " )");
    System.out.println();

    // Now let's go and ask for a protected resource!
    System.out.println("Now we're going to access a protected resource...");
    OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
    service.signRequest(accessToken, request);
    request.addHeader("Accept", "application/vnd.deere.axiom.v3+json");
    Response response = request.send();
    System.out.println("Got it! Lets see what we found...");
    System.out.println();
    System.out.println(response.getCode());
    System.out.println(response.getBody());

    System.out.println();
    System.out.println("Thats it man! Go and build something awesome with Scribe! :)");

  }
}
