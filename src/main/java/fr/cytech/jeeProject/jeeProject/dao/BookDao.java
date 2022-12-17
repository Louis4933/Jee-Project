package fr.cytech.jeeProject.jeeProject.dao;

import fr.cytech.jeeProject.jeeProject.beans.Book;
import fr.cytech.jeeProject.jeeProject.beans.SiteUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDao extends CrudRepository<Book, Long> {

    @Query(value = "SELECT u FROM Book u WHERE u.currentHolder = ?1")
    List<Book> getBookByHolder(SiteUser siteUser);

    @Query(value = "SELECT u FROM Book u WHERE u.title LIKE '%?1%'")
    List<Book> findBookWithSearchField(String query);

}
