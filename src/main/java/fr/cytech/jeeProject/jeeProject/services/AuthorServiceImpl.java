package fr.cytech.jeeProject.jeeProject.services;

import fr.cytech.jeeProject.jeeProject.beans.Author;
import fr.cytech.jeeProject.jeeProject.dao.AuthorDao;
import fr.cytech.jeeProject.jeeProject.services.interfaces.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorDao.save(author);
    }

    @Override
    public Author getAuthorById(Long authorId) {
        return authorDao.findById(authorId).orElse(null);
    }

    @Override
    public List<Author> getAuthorList() {
        return (List<Author>) authorDao.findAll();
    }

    @Override
    public Author updateAuthor(Author author, Long authorId) {

        Author authorDb = authorDao.findById(authorId).orElse(null);
        if(authorDb == null) return author;

        return authorDao.save(author);
    }

    @Override
    public void deleteAuthorById(Long authorId) {
        authorDao.deleteById(authorId);
    }
}
