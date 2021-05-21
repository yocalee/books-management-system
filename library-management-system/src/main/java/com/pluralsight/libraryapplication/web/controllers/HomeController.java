package com.pluralsight.libraryapplication.web.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.pluralsight.libraryapplication.models.Book;
import com.pluralsight.libraryapplication.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final BookService bookService;

    @Autowired
    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String showAll(Model model) throws UnirestException {
        bookService.importAll();
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "index";
    }
}
