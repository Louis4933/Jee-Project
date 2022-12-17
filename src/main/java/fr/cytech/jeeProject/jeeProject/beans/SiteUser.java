package fr.cytech.jeeProject.jeeProject.beans;

import fr.cytech.jeeProject.jeeProject.enums.UserRole;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SiteUser {

    private Long id;
    private String email;
    private String password;

    private String name;

    private String surname;

    private String address;

    private String cookieCode;
    private UserRole userRole;
    public List<Book> favorites = new ArrayList<>();
    public List<Book> bookCart = new ArrayList<>();

    public SiteUser(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "email", length = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", length = 64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "name", length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname", length = 64)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "address", length = 64)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "cookie", length = 64)
    public String getCookieCode() {
        return cookieCode;
    }

    public void setCookieCode(String cookieCode) {
        this.cookieCode = cookieCode;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Access(AccessType.PROPERTY)
    @ManyToMany(targetEntity = Book.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "user_favorites", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    public List<Book> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Book> favorites) {
        this.favorites = favorites;
    }

    @Access(AccessType.PROPERTY)
    @ManyToMany(targetEntity = Book.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "user_book_cart", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    public List<Book> getBookCart() {
        return bookCart;
    }

    public void setBookCart(List<Book> bookCart) {
        this.bookCart = bookCart;
    }
}
