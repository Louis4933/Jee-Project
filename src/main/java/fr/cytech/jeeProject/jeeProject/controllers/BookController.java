package fr.cytech.jeeProject.jeeProject.controllers;

import fr.cytech.jeeProject.jeeProject.beans.Book;
import fr.cytech.jeeProject.jeeProject.dao.BookDao;
import fr.cytech.jeeProject.jeeProject.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired private BookService bookService;

    @RequestMapping("/book/{id}")
    public String getBookById(@PathVariable(value = "id", required = false) long bookId, Model model){

        model.addAttribute("book", bookService.getBookById(bookId));

        return "books/book";
    }

    @RequestMapping("/books")
    public String getBooks(Model model){

        model.addAttribute("books", bookService.getBookList());

        return "books/list";
    }
}
