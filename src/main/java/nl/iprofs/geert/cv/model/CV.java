package nl.iprofs.geert.cv.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CV extends Entity implements Cloneable {

  private  String name;

  private String familyName;
  private String firstName;
  private Date birthDate;
  private String placeOfBirth;
  private String availability;
  private Date inITSince;

  private String position;

  private String introduction;

  private List<Experience> experiences = new ArrayList<Experience>();

  private List<KeywordCategory> keywordCategories = new ArrayList<KeywordCategory>();

  private List<Education> educations = new ArrayList<Education>();

  public String getName() {
    return name;
  }

  public void addExperience(Experience e) {
    experiences.add(e);
  }

  public void addKeywordCategory(KeywordCategory kc) {
    keywordCategories.add(kc);
  }

  public void addEducation(Education e) {
    educations.add(e);
  }

  public List<Experience> getExperiences() {
    return experiences;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

  public void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }

  public String getAvailability() {
    return availability;
  }

  public void setAvailability(String availability) {
    this.availability = availability;
  }

  public Date getInITSince() {
    return inITSince;
  }

  public void setInITSince(Date inITSince) {
    this.inITSince = inITSince;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public List<KeywordCategory> getKeywordCategories() {
    return keywordCategories;
  }


  public List<Education> getEducations() {
    return educations;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setExperiences(List<Experience> experiences) {
    this.experiences = experiences;
  }

  public void setKeywordCategories(List<KeywordCategory> keywordCategories) {
    this.keywordCategories = keywordCategories;
  }

  public void setEducations(List<Education> educations) {
    this.educations = educations;
  }

  public CV clone() throws CloneNotSupportedException {
    return (CV) super.clone();
  }
}