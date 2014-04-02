package org.scribe.builder.api;

import org.scribe.model.Token;
import org.scribe.model.Verb;

public class DeereApi extends DefaultApi10a
{
  private static final String AUTHORIZATION_URL = "https://my.deere.com/consentToUseOfData?oauth_token=%s";

  @Override
  public String getAccessTokenEndpoint()
  {
    return "https://api.deere.com/platform/oauth/access_token";
  }

  @Override
  public String getRequestTokenEndpoint()
  {
    return "https://api.deere.com/platform/oauth/request_token";
  }

  @Override
  public Verb getAccessTokenVerb()
  {
    return Verb.GET;
  }

  @Override
  public Verb getRequestTokenVerb()
  {
    return Verb.GET;
  }
  
  @Override
  public String getAuthorizationUrl(Token requestToken)
  {
    return String.format(AUTHORIZATION_URL, requestToken.getToken());
  }
}
