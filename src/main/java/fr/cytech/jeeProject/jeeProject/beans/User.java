package fr.cytech.jeeProject.jeeProject.beans;

import fr.cytech.jeeProject.jeeProject.enums.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Access(AccessType.PROPERTY)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", length = 64, nullable = false)
    private String email;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "surname", length = 64, nullable = false)
    private String surname;

    @Column(name = "address", length = 64, nullable = false)
    private String address;

    private UserRole userRole;

    @ManyToMany(targetEntity = Book.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_favorites", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    public List<Book> favorites = new ArrayList<>();

    public User(){

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Book> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Book> favorites) {
        this.favorites = favorites;
    }
}
