package test.andre.resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import test.andre.model.Author;
import test.andre.model.Publisher;

@Path("/front")
public class FrontEndResource {

    private static final String REST_URI 
      = "http://localhost:8090/publishers";

    private static final String AUTHOR_SVC = "http://localhost:8180/author-svc/rest/author";
  
    private Client client = ClientBuilder.newClient();
    
    @GET
    @Path("/publisher/{oid}")
    @Produces({ MediaType.APPLICATION_JSON})
    @Trace(operationName = "getPublisher")
    public Publisher getPublisher(@PathParam("oid") String name, @QueryParam("delay") Long myDelay, @QueryParam("backendDelay") Long delay) {
      Long sleepTime = new Long(0);
      
      if (myDelay != null) {
        try {
          Thread.sleep(myDelay.longValue());
        } catch (Exception e) {}
      }
      // 1.2343.234234234|1|1|1|#127.0.0.1:8080|#/portal/|#/testEntrySpan|1.2343.234234234
      String header = TraceContext.traceId()+"|1|1|1|#127.0.0.1:8070|#/front/publisher/"+name+"|#/getPublisher|"+TraceContext.traceId();
      System.out.println("Trace header: " + header);
      
      if (delay != null) sleepTime = delay;

      return useApacheClient(REST_URI, name, sleepTime);
      //  return client
      //    .target(REST_URI)
      //    .path(name)
      //    .queryParam("delay", sleepTime)
      //    .request(MediaType.APPLICATION_JSON)
      //    .header("sw3", header)
      //    .get(Publisher.class);
    } 

    public Publisher useApacheClient(String URI, String name, Long delay) {
      DefaultHttpClient httpClient = new DefaultHttpClient();
      String target = URI+"/"+name+"?delay="+delay.toString();
      System.out.println("Target URI: " + target);
		  HttpGet getRequest = new HttpGet(target);
		  getRequest.addHeader("accept", "application/json");
      Publisher resp = null;
      try {
        HttpResponse response = httpClient.execute(getRequest);  
        if (response.getStatusLine().getStatusCode() != 200) {
          throw new RuntimeException("Failed : HTTP error code : "
             + response.getStatusLine().getStatusCode());
        }
    
        ObjectMapper om = new ObjectMapper();
        //Publisher resp = null;
        try {
          resp = om.readValue(response.getEntity().getContent(), Publisher.class);
        } catch (Exception e) {
          e.printStackTrace();
        }
    
        httpClient.getConnectionManager().shutdown();
        //return resp;
      } catch (Exception e) {
        e.printStackTrace();
      }
      return resp;
    }

    @GET
    @Path("/author/{oid}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Author getAuthor(@PathParam("oid") String name, @QueryParam("delay") Long myDelay, @QueryParam("backendDelay") Long delay) {
      Long sleepTime = new Long(0);
      if (myDelay != null) {
        try {
          Thread.sleep(myDelay.longValue());
        } catch (Exception e) {}
      }
      if (delay != null) sleepTime = delay;
        return client
          .target(AUTHOR_SVC)
          .path(name)
          .queryParam("delay", sleepTime)
          .request(MediaType.APPLICATION_JSON)
          .get(Author.class);
    } 

    
}