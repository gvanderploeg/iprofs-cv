package nl.iprofs.geert.cv.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import nl.iprofs.geert.cv.model.CV;
import nl.iprofs.geert.cv.model.Experience;

public class CVBackend {

  private List<CV> cvs = Arrays.asList(
      new CV("Geert van der Ploeg",
          Arrays.asList(
              new Experience(
                  "Bij SURFnet",
                  new Date(System.currentTimeMillis()-86400*600),
                  new Date(),
                  "Ik heb flink m'n best gedaan",
                  Arrays.asList("OAuth", "SAML")))),
      new CV("Pietje Puk",
          Arrays.asList(
              new Experience(
                  "Bij de ING",
                  new Date(System.currentTimeMillis()-86400*300),
                  new Date(),
                  "Ik heb ook flink m'n best gedaan",
                  Arrays.asList("Spring", "JPA")))));

  public List<CV> getCVs() {
    return Collections.unmodifiableList(cvs);
  }

  public CV getCV(String name) {
    for (CV cv: cvs) {
      if (cv.getName().equals(name)) {
        return cv;
      }
    }
    return null;
  }
}
