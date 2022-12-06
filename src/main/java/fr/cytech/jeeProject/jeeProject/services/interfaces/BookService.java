package fr.cytech.jeeProject.jeeProject.services.interfaces;

import fr.cytech.jeeProject.jeeProject.beans.Book;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);
    Book getBookById(Long bookId);
    List<Book> getBookList();
    Book updateBook(Book book, Long bookId);
    void deleteBookById(Long bookId);

}
