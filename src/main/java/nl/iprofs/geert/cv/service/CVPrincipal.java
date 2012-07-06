package nl.iprofs.geert.cv.service;

import java.util.Collections;
import java.util.Set;

public class CVPrincipal {
  public enum Role {
    USER, ADMIN
  }


  private String username;
  private Set<Role> roles = Collections.emptySet();

  public CVPrincipal(String username, Set<Role> roles) {
    this.username = username;
    this.roles = roles;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
