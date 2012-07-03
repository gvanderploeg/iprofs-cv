package nl.iprofs.geert.cv;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.sun.jersey.api.json.JSONWithPadding;
import com.yammer.metrics.annotation.Timed;

import nl.iprofs.geert.cv.service.CVBackend;

@Path("/cv")
@Produces("application/x-javascript")
public class CVResource {

  private CVBackend backend;

  @GET
  @Path("{name}.json")
  @Timed
  public JSONWithPadding getCV(@PathParam("name") String name, @QueryParam("callback") String callback) {

    if (name != null) {
      return new JSONWithPadding(backend.getCV(name), callback);
    } else {
      return new JSONWithPadding(null, callback);
    }
  }

  public void setBackend(CVBackend backend) {
    this.backend = backend;
  }
}
