package nl.iprofs.geert.cv.model;

import java.util.Date;
import java.util.List;

public class Experience {

  private String title;
  private Date from;
  private Date until;
  private String introduction;
  private List<String> keywords;

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
