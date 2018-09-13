package test.andre.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import test.andre.model.Publisher;

@Path("/front")
public class FrontEndResource {

    private static final String REST_URI 
      = "http://localhost:8090/publishers";
  
    private Client client = ClientBuilder.newClient();
    
    @GET
    @Path("/{oid}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Publisher getPublisher(@PathParam("oid") int id) {
        return client
          .target(REST_URI)
          .path(String.valueOf(id))
          .request(MediaType.APPLICATION_JSON)
          .get(Publisher.class);
    } 


    
}