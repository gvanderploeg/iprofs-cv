package nl.iprofs.geert.cv.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Experience extends Entity {

  private String position;
  private String company;
  private String introduction;

  private String body;


  private Date from;
  private Date until;

  private List<KeywordCategory> keywordCategories = new ArrayList<KeywordCategory>();

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Date getFrom() {
    return from;
  }

  public void setFrom(Date from) {
    this.from = from;
  }

  public Date getUntil() {
    return until;
  }

  public void setUntil(Date until) {
    this.until = until;
  }


  public List<KeywordCategory> getKeywordCategories() {
    return keywordCategories;
  }

  public void setKeywordCategories(List<KeywordCategory> keywordCategories) {
    this.keywordCategories = keywordCategories;
  }

  public void addKeywordCategory(KeywordCategory kc) {
    keywordCategories.add(kc);
  }
}
