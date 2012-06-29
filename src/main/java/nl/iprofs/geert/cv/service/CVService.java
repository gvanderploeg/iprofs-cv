package nl.iprofs.geert.cv.service;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;

import nl.iprofs.geert.cv.CVConfiguration;
import nl.iprofs.geert.cv.CVResource;

public class CVService extends Service<CVConfiguration> {

  public static void main(String[] args) throws Exception {
    new CVService().run(args);
  }

  private CVService() {
    super("cv");
  }

  @Override
  protected void initialize(CVConfiguration cvConfiguration, Environment environment) throws Exception {
    environment.addResource(new CVResource());
  }
}
