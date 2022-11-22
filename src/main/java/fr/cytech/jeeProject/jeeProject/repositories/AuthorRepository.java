package fr.cytech.jeeProject.jeeProject.repositories;

import fr.cytech.jeeProject.jeeProject.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
