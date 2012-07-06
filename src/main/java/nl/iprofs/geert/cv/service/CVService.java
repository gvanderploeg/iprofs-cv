package nl.iprofs.geert.cv.service;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;

import nl.iprofs.geert.cv.CVConfiguration;
import nl.iprofs.geert.cv.CVResource;

public class CVService extends Service<CVConfiguration> {

  public static void main(String[] args) throws Exception {
    final CVService cvService = new CVService();
    cvService.run(args);
  }

  private CVService() {
    super("cv");
  }

  @Override
  protected void initialize(CVConfiguration cvConfiguration, Environment environment) throws Exception {
    CVResource cvResource = new CVResource();
    cvResource.setBackend(new CVBackend());
    environment.addResource(cvResource);
    environment.addProvider(new CVConfigurationAuthenticator());
  }
}
