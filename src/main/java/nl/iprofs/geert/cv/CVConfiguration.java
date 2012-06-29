package nl.iprofs.geert.cv;

import com.yammer.dropwizard.config.Configuration;

import org.codehaus.jackson.annotate.JsonProperty;

public class CVConfiguration extends Configuration {


  @JsonProperty
  private int port = 8080;

  public int getPort() {
    return port;
  }
}
