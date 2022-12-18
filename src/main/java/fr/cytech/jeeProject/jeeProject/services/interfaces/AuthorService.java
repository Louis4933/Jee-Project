package fr.cytech.jeeProject.jeeProject.services.interfaces;

import fr.cytech.jeeProject.jeeProject.beans.Author;

import java.util.List;

public interface AuthorService {

    Author saveAuthor(Author author);
    Author getAuthorById(Long authorId);
    Author getAuthorByName(String firstName, String lastName);
    List<Author> getAuthorList();
    Author updateAuthor(Author author, Long authorId);
    void deleteAuthorById(Long authorId);

}
