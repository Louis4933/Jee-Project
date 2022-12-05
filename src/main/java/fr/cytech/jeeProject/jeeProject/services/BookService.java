package fr.cytech.jeeProject.jeeProject.services;

import fr.cytech.jeeProject.jeeProject.dao.BookDao;
import fr.cytech.jeeProject.jeeProject.dao.DaoManager;
import fr.cytech.jeeProject.jeeProject.beans.Book;
import org.springframework.stereotype.Component;

@Component(value = "bookService")
public class BookService {

    private BookDao bookDao;

    public BookService(DaoManager daoManager){
        this.bookDao = daoManager.getBookDao();
    }

    public Book getBookById(int id){
        return this.bookDao.get(id).orElse(null);
    }

}
