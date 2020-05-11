package test.andre.publisher.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import test.andre.publisher.model.Publisher;
import test.andre.publisher.repository.PublisherRepository;

@Path("/publishers")
public class PublisherResource {

    @Autowired
    private PublisherRepository repository;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Publisher> getAllPublishers() {
        ArrayList<Publisher> output = new ArrayList<Publisher>();
        repository.findAll().forEach(p -> output.add(p));
        return output;
    }
    @GET
    @Path("/{oid}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Publisher getPublisherByName(@PathParam("oid") String name, @QueryParam("delay") Long delay, @HeaderParam("sw3") String header) {
        System.out.println("sw3 header: " + header);
        Object obj = repository.getPublisherByName(name);
        try {
        //if (delay != null) Thread.sleep(delay.longValue());
        if (delay != null) spin(delay.intValue());
        } catch (Exception e) {}
        if (obj != null) { return (Publisher) obj;} else return null;
    } 

   private static void spin(int milliseconds) {
       long sleepTime = milliseconds*1000000L; // convert to nanoseconds
       long startTime = System.nanoTime();
       while ((System.nanoTime() - startTime) < sleepTime) {}
   }


}
