package fr.cytech.jeeProject.jeeProject.beans;

import fr.cytech.jeeProject.jeeProject.enums.BookFormat;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    private Long id;

    private String title, resume, publicationDate, isbn, coverImage;

    private int numberPage;

    private BookFormat bookFormat;

    private Publisher publisher;

    private SiteUser currentHolder;

    private List<Author> authors = new ArrayList<>();

    public Book(String title, String resume, int numberPage, String publicationDate, String isbn, String coverImage, BookFormat bookFormat, Publisher publisher) {
        this.title = title;
        this.resume = resume;
        this.numberPage = numberPage;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.coverImage = coverImage;
        this.bookFormat = bookFormat;
        this.publisher = publisher;
    }

    public Book() {}


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "resume", length = 4096)
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Column(name = "numberPage", length = 64)
    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    @Column(name = "publicationDate", length = 64)
    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Column(name = "isbn", length = 64)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column(name = "coverImage")
    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public BookFormat getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(BookFormat bookFormat) {
        this.bookFormat = bookFormat;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_user_id")
    public SiteUser getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(SiteUser currentHolder) {
        this.currentHolder = currentHolder;
    }

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id != null ? id.equals(book.id) : book.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
