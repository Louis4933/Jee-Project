package fr.cytech.jeeProject.jeeProject.services.interfaces;

import fr.cytech.jeeProject.jeeProject.beans.Book;
import fr.cytech.jeeProject.jeeProject.beans.SiteUser;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);
    Book getBookById(Long bookId);
    List<Book> getBookByHolder(SiteUser siteUser);
    List<Book> getBookList();
    List<Book> findBookWithSearchField(String query);
    Book updateBook(Book book, Long bookId);
    void deleteBookById(Long bookId);

}
