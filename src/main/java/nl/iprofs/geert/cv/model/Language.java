package nl.iprofs.geert.cv.model;

public class Language extends Entity {

  /**
   * See http://en.wikipedia.org/wiki/ILR_scale
   */
  public enum Proficiency {
    Elementary, LimitedWorking, ProfessionalWorking, FullProfessional, Native
  }

  private String name;
  private Proficiency speaking;
  private Proficiency writing;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Proficiency getSpeaking() {
    return speaking;
  }

  public void setSpeaking(Proficiency speaking) {
    this.speaking = speaking;
  }

  public Proficiency getWriting() {
    return writing;
  }

  public void setWriting(Proficiency writing) {
    this.writing = writing;
  }
}
