package fr.cytech.jeeProject.jeeProject.bootstrap;

import fr.cytech.jeeProject.jeeProject.beans.Author;
import fr.cytech.jeeProject.jeeProject.beans.Book;
import fr.cytech.jeeProject.jeeProject.beans.Publisher;
import fr.cytech.jeeProject.jeeProject.beans.SiteUser;
import fr.cytech.jeeProject.jeeProject.dao.AuthorDao;
import fr.cytech.jeeProject.jeeProject.dao.BookDao;
import fr.cytech.jeeProject.jeeProject.dao.PublisherDao;
import fr.cytech.jeeProject.jeeProject.dao.SiteUserDao;
import fr.cytech.jeeProject.jeeProject.enums.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final SiteUserDao siteUserDao;

    public BootStrapData(AuthorDao authorDao, BookDao bookDao, PublisherDao publisherDao,
                         SiteUserDao siteUserDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.siteUserDao = siteUserDao;
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

        SiteUser siteUser = new SiteUser();
        siteUser.setName("Dorian");
        siteUser.setSurname("Carlone");
        siteUser.setUserRole(UserRole.DEFAULT);
        siteUser.setAddress("17 Rue Bernadotte");
        siteUser.setPassword("rolandgarros");
        siteUser.setEmail("dorian.carlone@yahoo.fr");
        siteUser.setCookieCode("3e584755-1bdf-4afc-a02b-74f9a0d7c88d");

        siteUserDao.save(siteUser);

        authorDao.save(eric);
        bookDao.save(ddd);
        publisherDao.save(publisher);

        System.out.println("Number of Books: " + bookDao.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
}
