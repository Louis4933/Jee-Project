package fr.cytech.jeeProject.jeeProject.repositories;

import fr.cytech.jeeProject.jeeProject.beans.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
