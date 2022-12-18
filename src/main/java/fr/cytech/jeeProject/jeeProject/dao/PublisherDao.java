package fr.cytech.jeeProject.jeeProject.dao;

import fr.cytech.jeeProject.jeeProject.beans.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PublisherDao extends CrudRepository<Publisher, Long> {

    @Query(value = "SELECT u FROM Publisher u WHERE u.name = ?1")
    Publisher getPublisherByName(String publisherName);

}
