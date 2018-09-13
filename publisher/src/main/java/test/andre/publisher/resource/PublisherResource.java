package test.andre.publisher.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
        return repository.getAllPublishers();
    }
    @GET
    @Path("/{oid}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Publisher getPublisher(@PathParam("oid") int id) {
        return repository.getPublisher(id);
    } 




}