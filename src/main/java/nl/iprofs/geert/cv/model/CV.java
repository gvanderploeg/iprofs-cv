package nl.iprofs.geert.cv.model;

import java.util.List;

public class CV {

  private final String name;
  private final List<Experience> experiences;

  public CV(String name, List<Experience> experiences) {
    this.name = name;
    this.experiences = experiences;
  }

  public String getName() {
    return name;
  }

  public List<Experience> getExperiences() {
    return experiences;
  }
}
