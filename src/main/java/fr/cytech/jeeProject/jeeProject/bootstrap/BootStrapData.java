package fr.cytech.jeeProject.jeeProject.bootstrap;

import fr.cytech.jeeProject.jeeProject.beans.Author;
import fr.cytech.jeeProject.jeeProject.beans.Book;
import fr.cytech.jeeProject.jeeProject.beans.Publisher;
import fr.cytech.jeeProject.jeeProject.dao.AuthorDao;
import fr.cytech.jeeProject.jeeProject.dao.BookDao;
import fr.cytech.jeeProject.jeeProject.dao.PublisherDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final PublisherDao publisherDao;

    public BootStrapData(AuthorDao authorDao, BookDao bookDao, PublisherDao publisherDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in boostrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersbourg");
        publisher.setState("FL");

        publisherDao.save(publisher);

        System.out.println("Publisher count : " + publisherDao.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorDao.save(eric);
        bookDao.save(ddd);
        publisherDao.save(publisher);

        System.out.println("Number of Books: " + bookDao.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
}
