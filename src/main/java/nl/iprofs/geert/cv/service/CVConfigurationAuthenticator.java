package nl.iprofs.geert.cv.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Optional;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;

public class CVConfigurationAuthenticator implements Authenticator<BasicCredentials, CVPrincipal> {

  private Map<String, String> users = new HashMap<String, String>();

  public CVConfigurationAuthenticator() {
    users.put("geert", "password");
  }

  @Override
  public Optional<CVPrincipal> authenticate(BasicCredentials credentials) throws AuthenticationException {

    final String username = credentials.getUsername();
    String password = credentials.getPassword();
    if (users.containsKey(username) && users.get(username).equals(password)) {
      Set<CVPrincipal.Role> roles = Collections.singleton(CVPrincipal.Role.USER);
      return Optional.of(new CVPrincipal(username, roles));
    }
    return Optional.absent();
  }
}
