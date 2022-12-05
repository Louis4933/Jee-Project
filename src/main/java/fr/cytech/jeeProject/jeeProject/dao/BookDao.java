package fr.cytech.jeeProject.jeeProject.dao;

import fr.cytech.jeeProject.jeeProject.domain.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BookDao implements Dao<Book> {

    private DaoManager daoManager;

    public BookDao(DaoManager daoManager){
        this.daoManager = daoManager;
    }

    @Override
    public Optional<Book> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Book> getAll() {

        List<Book> bookList = new ArrayList<>();

        try {
            Connection connection = daoManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Books");
            while (rs.next()){
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setTitle(rs.getString("title"));
                bookList.add(book);
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return bookList;
    }

    @Override
    public int save(Book book) {
        return 0;
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(Book book) {

    }
}
