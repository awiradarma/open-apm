package test.andre.publisher.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import test.andre.publisher.model.Publisher;

@Component
public class PublisherRepositoryImpl implements PublisherRepository{

    private List<Publisher> publishers;
    
    PublisherRepositoryImpl() {
        publishers = new ArrayList<Publisher>();
        publishers.add(new Publisher(1, "O'Reilly"));
        publishers.add(new Publisher(2, "Manning"));
    }

    
    public List<Publisher> getAllPublishers() {
        return publishers;
    }

    public Publisher getPublisher(int id) {
        for (Publisher p : publishers) {
            if (p.getId() == id) return p;
        }
            return null;
    }

}