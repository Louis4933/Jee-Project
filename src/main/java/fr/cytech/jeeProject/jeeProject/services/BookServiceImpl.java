package fr.cytech.jeeProject.jeeProject.services;

import fr.cytech.jeeProject.jeeProject.beans.Book;
import fr.cytech.jeeProject.jeeProject.beans.SiteUser;
import fr.cytech.jeeProject.jeeProject.dao.BookDao;
import fr.cytech.jeeProject.jeeProject.services.interfaces.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book saveBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookDao.findById(bookId).orElse(null);
    }

    @Override
    public List<Book> getBookByHolder(SiteUser siteUser) {
        return bookDao.getBookByHolder(siteUser);
    }

    @Override
    public List<Book> getBookList() {
        return (List<Book>) bookDao.findAll();
    }

    @Override
    public List<Book> findBookWithSearchField(String query) {
        return bookDao.findBookWithSearchField(query);
    }

    @Override
    public Book updateBook(Book book, Long bookId) {

        Book bookDb = bookDao.findById(bookId).orElse(null);
        if(bookDb == null) return book;

        return bookDao.save(book);
    }

    @Override
    public void deleteBookById(Long bookId) {
        bookDao.deleteById(bookId);
    }
}
