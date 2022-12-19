package fr.cytech.jeeProject.jeeProject.controllers;

import fr.cytech.jeeProject.jeeProject.beans.*;
import fr.cytech.jeeProject.jeeProject.controllers.Utils.ModelDefaultAttributes;
import fr.cytech.jeeProject.jeeProject.dao.*;
import fr.cytech.jeeProject.jeeProject.enums.BookFormat;
import fr.cytech.jeeProject.jeeProject.enums.UserRole;
import fr.cytech.jeeProject.jeeProject.services.interfaces.AuthorService;
import fr.cytech.jeeProject.jeeProject.services.interfaces.BookService;
import fr.cytech.jeeProject.jeeProject.services.interfaces.PublisherService;
import fr.cytech.jeeProject.jeeProject.services.interfaces.SiteUserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    private SiteUserService siteUserService;
    @Autowired
    private SiteUserDao siteUserDao;
    @Autowired
    private BookService bookService;
    @Autowired
    private PublisherDao publisherDao;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private LibraryDao libraryDao;

    @RequestMapping("/admin")
    public ModelAndView getRegisterPage(HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser == null || siteUser.getUserRole() != UserRole.ADMIN){ return new ModelAndView("redirect:/"); }

        model.addAttribute("siteUser", siteUser);
        model.addAttribute("booksWaitingCollect", siteUser.getLibrary().getBooks().stream().filter(book -> book.getCurrentHolder() != null && book.getDeadline() == null).collect(Collectors.toList()));
        model.addAttribute("booksAlreadyCollected", siteUser.getLibrary().getBooks().stream().filter(book -> book.getCurrentHolder() != null && book.getDeadline() != null).collect(Collectors.toList()));

        return new ModelAndView("adminPanel/admin");
    }

    @RequestMapping("/add/book")
    public ModelAndView addBook(HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser == null || siteUser.getUserRole() != UserRole.ADMIN){ return new ModelAndView("redirect:/"); }

        List<String> formatOptions = Arrays.stream(BookFormat.values()).map(BookFormat::getName).collect(Collectors.toList());
        model.addAttribute("formatOptions", formatOptions);

        List<String> authorOptions = authorService.getAuthorList().stream().map(author -> author.getFirstName() + " " + author.getLastName()).collect(Collectors.toList());
        model.addAttribute("authorOptions", authorOptions);

        List<String> publisherOptions = publisherService.getPublisherList().stream().map(Publisher::getName).collect(Collectors.toList());
        model.addAttribute("publisherOptions", publisherOptions);

        return new ModelAndView("adminPanel/addBook");
    }

    @RequestMapping("/add/author")
    public ModelAndView addAuthor(HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser == null || siteUser.getUserRole() != UserRole.ADMIN){ return new ModelAndView("redirect:/"); }

        return new ModelAndView("adminPanel/addAuthor");
    }

    @RequestMapping("/add/publisher")
    public ModelAndView addPublisher(HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser == null || siteUser.getUserRole() != UserRole.ADMIN){ return new ModelAndView("redirect:/"); }

        return new ModelAndView("adminPanel/addPublisher");
    }

    @RequestMapping(value = "/create/book", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerBook(@RequestBody MultiValueMap<String, String> formData, HttpServletRequest request){

        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser == null || siteUser.getUserRole() != UserRole.ADMIN){ return new ModelAndView("redirect:/"); }

        Book book = new Book();
        book.setTitle(formData.getFirst("title"));
        book.setResume(formData.getFirst("resume"));
        book.setPublicationDate(formData.getFirst("publicationDate"));
        book.setIsbn(formData.getFirst("isbn"));
        if(formData.containsKey("numberPage") && formData.get("numberPage").size() > 0) book.setNumberPage(Integer.parseInt(formData.getFirst("numberPage")));
        book.setCoverImage(formData.getFirst("coverImage"));
        book.setBookFormat(Arrays.stream(BookFormat.values()).filter(bookFormat -> bookFormat.getName().equalsIgnoreCase(formData.getFirst("bookFormat"))).findFirst().get());
        book.setPublisher(publisherService.getPublisherByName(formData.getFirst("publisher")));
        for (String authors : formData.get("authors")) {
            book.getAuthors().add(authorService.getAuthorByName(authors.split(" ")[0], authors.split(" ")[1]));
        }

        bookDao.save(book);

        Library library = siteUser.getLibrary();
        library.getBooks().add(book);
        libraryDao.save(library);

        formData.clear();

        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/create/author", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerAuthor(@RequestBody MultiValueMap<String, String> formData, HttpServletResponse response){

        Author author = new Author();
        author.setFirstName(formData.getFirst("firstName"));
        author.setLastName(formData.getFirst("lastName"));
        author.setImageUrl(formData.getFirst("imageUrl"));

        authorDao.save(author);

        formData.clear();

        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/create/publisher", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerPublisher(@RequestBody MultiValueMap<String, String> formData, HttpServletResponse response){

        Publisher publisher = new Publisher();
        publisher.setName(formData.get("name").get(0));
        publisher.setAddressLine(formData.get("address").get(0));

        publisherDao.save(publisher);

        formData.clear();

        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/validate/{id}")
    public ModelAndView validateBookState(@PathVariable(value = "id", required = true) Long id, @RequestParam(value = "type", required = true) String type, HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        Book book = bookService.getBookById(id);
        if(book != null){

            if(type.equalsIgnoreCase("collected")){

                Date dt = new Date();
                DateTime dtOrg = new DateTime(dt);
                DateTime dtPlusOne = dtOrg.plusDays(10);

                book.setDeadline(dtPlusOne.toDate());
            }
            if(type.equalsIgnoreCase("returned")){
                book.setDeadline(null);
                book.setCurrentHolder(null);
            }

            bookDao.save(book);
        }

        return new ModelAndView("redirect:/admin");
    }

}
