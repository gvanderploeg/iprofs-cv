package nl.iprofs.geert.cv.model;

import java.util.List;

public class KeywordCategory extends Entity {
  private String name;
  private List<String> keywords;

  public KeywordCategory() {
  }

  public KeywordCategory(String name, List<String> keywords) {
    this.name = name;
    this.keywords = keywords;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<String> keywords) {
    this.keywords = keywords;
  }
}
