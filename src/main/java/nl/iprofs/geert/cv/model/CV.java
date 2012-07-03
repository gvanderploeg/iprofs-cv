package nl.iprofs.geert.cv.model;

import java.util.List;

import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

// workaround for http://code.google.com/p/orient/issues/detail?id=869
@JsonIgnoreProperties({"handler"})
public class CV {

  @Id
  private Object id;

  private  String name;
  private List<Experience> experiences;

  public CV() {
  }

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
