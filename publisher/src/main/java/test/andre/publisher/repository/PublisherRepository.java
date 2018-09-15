package test.andre.publisher.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import test.andre.publisher.model.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

    //List<Publisher> getAllPublishers();
    Publisher getPublisherByName(String name);
}