package fr.cytech.jeeProject.jeeProject.dao;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class DaoManager {

    private String databaseUrl, databaseName, user, password;
    private int port;

    public DaoManager(String databaseUrl, String databaseName, String user, String password, int port){
        this.databaseUrl = databaseUrl;
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;
        this.port = port;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://" + this.databaseUrl + ":" + this.port + "/" + this.databaseName,this.user,this.password);
    }

    public BookDao getBookDao() {
        return new BookDao(this);
    }

}
