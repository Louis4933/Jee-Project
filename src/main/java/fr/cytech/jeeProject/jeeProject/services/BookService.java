package fr.cytech.jeeProject.jeeProject.services;

import fr.cytech.jeeProject.jeeProject.dao.BookDao;
import fr.cytech.jeeProject.jeeProject.dao.DaoManager;
import fr.cytech.jeeProject.jeeProject.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
