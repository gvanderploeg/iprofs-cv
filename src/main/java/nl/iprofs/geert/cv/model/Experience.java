package nl.iprofs.geert.cv.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

// workaround for http://code.google.com/p/orient/issues/detail?id=869
@JsonIgnoreProperties({"handler"})
public class Experience {

  @Id
  private Object id;


  private String title;
  private Date from;
  private Date until;
  private String introduction;
  private List<String> keywords;

  public Experience() {
  }

  public Experience(String title, Date from, Date until, String introduction, List<String> keywords) {
    this.title = title;
    this.from = from;
    this.until = until;
    this.introduction = introduction;
    this.keywords = keywords;
  }

  public String getTitle() {
    return title;
  }

  public Date getFrom() {
    return from;
  }

  public Date getUntil() {
    return until;
  }

  public String getIntroduction() {
    return introduction;
  }

  public List<String> getKeywords() {
    return keywords;
  }
}
