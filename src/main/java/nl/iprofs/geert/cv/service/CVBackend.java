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

public class CVBackend {

  private static final Logger LOG = LoggerFactory.getLogger(CVBackend.class);

  private static final String DB_URL = "local:target/cvdb";


  private List<CV> cvs = Arrays.asList(
      new CV("Geert van der Ploeg",
          Arrays.asList(
              new Experience(
                  "Bij SURFnet",
                  new Date(System.currentTimeMillis() - 86400 * 600),
                  new Date(),
                  "Ik heb flink m'n best gedaan",
                  Arrays.asList("OAuth", "SAML")))),
      new CV("Pietje Puk",
          Arrays.asList(
              new Experience(
                  "Bij de ING",
                  new Date(System.currentTimeMillis() - 86400 * 300),
                  new Date(),
                  "Ik heb ook flink m'n best gedaan",
                  Arrays.asList("Spring", "JPA")))));
  private static final String DEEP = "CV:-1 Experience.handler:0";

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
      }
      return (CV) db.getUserObjectByRecord(result.get(0), DEEP);
    } finally {
      db.close();
    }
  }

}
