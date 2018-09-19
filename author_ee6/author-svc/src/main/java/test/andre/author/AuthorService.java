package test.andre.author;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/author")
public class AuthorService {

    @GET
    @Path("/{authorid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Author getAuthorByName(@PathParam("authorid") String name, @QueryParam("delay") Long sleepTime) {
        try {
            if (sleepTime != null) Thread.sleep(sleepTime.longValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Got a request for " + name);
        return new Author(name, name, "Smith");
    }

}