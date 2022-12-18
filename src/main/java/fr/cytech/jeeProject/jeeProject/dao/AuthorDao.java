package fr.cytech.jeeProject.jeeProject.dao;

import fr.cytech.jeeProject.jeeProject.beans.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthorDao extends CrudRepository<Author, Long> {

    @Query(value = "SELECT u FROM Author u WHERE u.firstName = ?1 AND u.lastName = ?2")
    Author getAuthorByName(String firstName, String lastName);

}
