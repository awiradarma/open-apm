package test.andre.publisher.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
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
    public Publisher getPublisherByName(@PathParam("oid") String name, @QueryParam("delay") Long delay) {
        Object obj = repository.getPublisherByName(name);
        try {
        if (delay != null) Thread.sleep(delay.longValue());
        } catch (Exception e) {}
        if (obj != null) { return (Publisher) obj;} else return null;
    } 




}