package nl.iprofs.geert.cv.model;

import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

// workaround for http://code.google.com/p/orient/issues/detail?id=869
@JsonIgnoreProperties({"handler"})
public abstract class Entity {

  @Id
  protected Object id;
}
