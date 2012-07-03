package nl.iprofs.geert.cv.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Iterables;
import com.orientechnologies.orient.core.exception.OStorageException;
import com.orientechnologies.orient.core.query.nativ.ONativeSynchQuery;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNative;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.iprofs.geert.cv.model.CV;
import nl.iprofs.geert.cv.model.Experience;
import nl.iprofs.geert.cv.model.KeywordCategory;

public class CVBackend {

  private static final Logger LOG = LoggerFactory.getLogger(CVBackend.class);

  private static final String DB_URL = "local:target/cvdb";

  private List<CV> cvs = new ArrayList<CV>();


  {
    CV cv = new CV();
    cv.setName("geert");
    cv.setAvailability("Parttime, 80%");
    cv.setBirthDate(new Date(1980, 4, 16));
    cv.setFamilyName("van der Ploeg");
    cv.setFirstName("Geert");
    cv.setInITSince(new Date(2000, 11, 1));
    cv.setPlaceOfBirth("Alkmaar");
    cv.setIntroduction("Geert is een breed ontwikkelde software developer. Sinds 1997 is hij bezig met ICT.\n\n" +
        "In de loop van de jaren heeft Geert een ruime ervaring opgedaan met webdevelopment en infrastructuur, vooral op het Unix platform. Geert beheerst niet alleen Java maar heeft ook veel ervaring opgedaan met scripttalen als PHP, Perl en Bash, en met veel networking-aspecten zoals routering, firewalling, IP-migraties en monitoring. Zijn analytische vermogen en brede ontwikkeling maken dat Geert een goede overview heeft over alle technische aspecten van projecten. \n\n" +
        "Geert is leergierig en beschikt over prima communicatieve vaardigheden.");
    cv.setPosition("Software-architect");
    cv.addKeywordCategory(new KeywordCategory("Programmeertalen", Arrays.asList("Java J2EE", "Javascript", "PHP",
        "Perl", "Bash", "SQL")));
    cv.addKeywordCategory(new KeywordCategory("Scripting", Arrays.asList("XSLT", "CSS", "Ant", "Maven")));
    cv.addKeywordCategory(new KeywordCategory("IDE's", Arrays.asList("Eclipse", "IntelliJ")));
    cv.addKeywordCategory(new KeywordCategory("Application Servers", Arrays.asList("Apache Tomcat 4, 5, 6, 7",
        "JBoss AS",
            "IBM WebSphere 5, 6")));

    Experience e1 = new Experience();
    e1.setCompany("SURFnet");
    e1.setDescription("Provisioning/Deprovisioning in identity federations\n" +
        "Periode: juli 2011 - Heden\n" +
        "Inzet en customization van een Open source Identity Managementcomponent (Syncope) voor provisioning van user accounts vanuit een identity federation naar service providers.");
    e1.setFrom(new Date(2011, 0, 1));
    e1.setUntil(new Date(2011, 11, 31));
    e1.setPosition("Software-architect");
    e1.addKeywordCategory(new KeywordCategory("Frameworks", Arrays.asList("Spring", "Wicket")));
    cv.addExperience(e1);


    cvs.add(cv);
  }


  private static final String DEEP = "CV:-1";

  // Init with static content
  public CVBackend() {
    OObjectDatabaseTx db = null;
    try {
      db = db();
      db.getEntityManager().registerEntityClasses("nl.iprofs.geert.cv.model");
    } catch (OStorageException e) {
      db = new OObjectDatabaseTx(DB_URL).create();
      try {
        db.getEntityManager().registerEntityClasses("nl.iprofs.geert.cv.model");
        for (CV cv : cvs) {
          db.save(cv);
        }
      } finally {
        db.close();
      }
    } finally {
      if (db != null) {
        db.close();
      }
    }
  }

  private OObjectDatabaseTx db() {
    return OObjectDatabasePool.global().acquire(DB_URL, "admin", "admin");
  }

  /**
   * Get all from repository.
   * @return
   */
  public List<CV> getCVs() {
    final OObjectDatabaseTx db = db();
    final ArrayList<CV> cvs = new ArrayList<CV>();
    try {
      Iterables.addAll(cvs, db.browseClass(CV.class));
    } finally {
      db.close();
    }
    return cvs;
  }

  public CV getCV(final String name) {
    final OObjectDatabaseTx db = db();

    try {
      List<ODocument> result = new ONativeSynchQuery<OQueryContextNative>(db.getUnderlying(), "CV",
          new OQueryContextNative()) {
        @Override
        public boolean filter(OQueryContextNative iRecord) {
          return
              iRecord.field("name")
                  .like(name).go();
        };
      }.execute();
      if (result.size() > 1) {
        throw new IllegalStateException("More than one document found for primary key '" + name + "': " + result.size());
      } else if (result.size() == 0) {
        return null;
      } else {
        return (CV) db.getUserObjectByRecord(result.get(0), DEEP);
      }
    } finally {
      db.close();
    }
  }

}
