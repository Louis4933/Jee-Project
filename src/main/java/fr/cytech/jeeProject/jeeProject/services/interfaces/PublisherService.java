package fr.cytech.jeeProject.jeeProject.services.interfaces;

import fr.cytech.jeeProject.jeeProject.beans.Publisher;

import java.util.List;

public interface PublisherService {

    Publisher savePublisher(Publisher publisher);
    Publisher getPublisherById(Long publisherId);
    Publisher getPublisherByName(String publisherName);
    List<Publisher> getPublisherList();
    Publisher updatePublisher(Publisher publisher, Long publisherId);
    void deletePublisherById(Long publisherId);

}
