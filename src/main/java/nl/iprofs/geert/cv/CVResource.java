package nl.iprofs.geert.cv;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import nl.iprofs.geert.cv.model.CV;
import nl.iprofs.geert.cv.service.CVBackend;

@Path("/cv")
@Produces(MediaType.APPLICATION_JSON)
public class CVResource {

  private CVBackend backend;

  @GET
  @Timed
  public CV sayHello(@QueryParam("name") Optional<String> name) {
    return backend.getCV(name.toString());
  }

}
