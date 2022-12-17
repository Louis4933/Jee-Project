package fr.cytech.jeeProject.jeeProject.controllers;

import fr.cytech.jeeProject.jeeProject.beans.Book;
import fr.cytech.jeeProject.jeeProject.beans.Library;
import fr.cytech.jeeProject.jeeProject.beans.SiteUser;
import fr.cytech.jeeProject.jeeProject.controllers.Utils.ModelDefaultAttributes;
import fr.cytech.jeeProject.jeeProject.services.interfaces.LibraryService;
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
public class LibraryController {

    @Autowired private LibraryService libraryService;
    @Autowired private SiteUserService siteUserService;

    @RequestMapping("/libraries")
    public String getLibraries(HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        model.addAttribute("libraries", libraryService.getLibraries());

        return "librairies/list";
    }

    @RequestMapping("/library/{urlShort}")
    public String getLibrary(@PathVariable(value = "urlShort", required = false) String libraryUrlShort, @RequestParam(value = "search", required = false) String query, @RequestParam(value = "wishlist", required = false) String wishlist, @RequestParam(value = "disponible", required = false) String disponible, HttpServletRequest request, Model model){

        ModelDefaultAttributes.getDefaultAttributes(model, request, siteUserService);
        Library library = libraryService.getLibraryByUrlShort(libraryUrlShort);
        if(library == null){ return "librairies/list"; }

        List<Book> books = new ArrayList<>(library.getBooks());
        model.addAttribute("library", library);
        model.addAttribute("books", books);
        SiteUser siteUser = ModelDefaultAttributes.isUserConnected(request, siteUserService);
        if(siteUser != null){
            model.addAttribute("siteUser", siteUser);
        }
        if(query != null){
            books = books.stream().filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
            model.addAttribute("books", books);
        }
        if(wishlist != null && wishlist.equalsIgnoreCase("true") && siteUser != null){
            books = books.stream().filter(book -> siteUser.favorites.contains(book)).collect(Collectors.toList());
            model.addAttribute("books", books);
        }
        if(disponible != null && disponible.equalsIgnoreCase("true")){
            books = books.stream().filter(book -> book.getCurrentHolder() == null).collect(Collectors.toList());
            model.addAttribute("books", books);
        }

        return "librairies/library";
    }

}
