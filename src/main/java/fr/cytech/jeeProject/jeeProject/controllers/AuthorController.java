package fr.cytech.jeeProject.jeeProject.controllers;

import fr.cytech.jeeProject.jeeProject.beans.Author;
import fr.cytech.jeeProject.jeeProject.beans.Book;
import fr.cytech.jeeProject.jeeProject.beans.SiteUser;
import fr.cytech.jeeProject.jeeProject.controllers.Utils.ModelDefaultAttributes;
import fr.cytech.jeeProject.jeeProject.services.interfaces.AuthorService;
import fr.cytech.jeeProject.jeeProject.services.interfaces.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired private SiteUserService siteUserService;

    @RequestMapping("/authors")
    public String getAuthors(@RequestParam(value = "search", required = false) String query, HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        List<Author> authors = new ArrayList<>(authorService.getAuthorList());
        model.addAttribute("authors", authors);
        if(query != null){
            authors = authors.stream().filter(author -> (author.getFirstName() + " " + author.getLastName()).toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
            model.addAttribute("authors", authors);
        }

        return "authors/list";
    }

    @RequestMapping("/author/{id}")
    public String getAuthor(@PathVariable(value = "id", required = false) Long id, @RequestParam(value = "search", required = false) String query, HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        Author author = authorService.getAuthorById(id);
        if(author == null){ return "authors/list"; }

        List<Book> books = new ArrayList<>(author.getBooks());
        model.addAttribute("author", author);
        model.addAttribute("books", books);
        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser != null){
            model.addAttribute("siteUser", siteUser);
        }
        if(query != null){
            books = books.stream().filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
            model.addAttribute("books", books);
        }

        return "authors/author";
    }
}
