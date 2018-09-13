package test.andre.publisher.repository;

import java.util.List;

import test.andre.publisher.model.Publisher;

public interface PublisherRepository {

    List<Publisher> getAllPublishers();
    Publisher getPublisher(int id);
}