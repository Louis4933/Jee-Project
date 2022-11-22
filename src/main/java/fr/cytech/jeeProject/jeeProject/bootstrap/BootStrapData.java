package fr.cytech.jeeProject.jeeProject.bootstrap;

import fr.cytech.jeeProject.jeeProject.domain.Author;
import fr.cytech.jeeProject.jeeProject.domain.Book;
import fr.cytech.jeeProject.jeeProject.domain.Publisher;
import fr.cytech.jeeProject.jeeProject.repositories.AuthorRepository;
import fr.cytech.jeeProject.jeeProject.repositories.BookRepository;
import fr.cytech.jeeProject.jeeProject.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in boostrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersbourg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publisher count : " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
}
