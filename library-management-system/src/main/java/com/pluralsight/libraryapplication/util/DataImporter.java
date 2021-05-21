package com.pluralsight.libraryapplication.util;

import com.google.gson.*;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.pluralsight.libraryapplication.models.Author;
import com.pluralsight.libraryapplication.models.Book;
import com.pluralsight.libraryapplication.models.dtos.BookDTO;
import com.pluralsight.libraryapplication.services.AuthorService;
import com.pluralsight.libraryapplication.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

@Component
public class DataImporter {
    private static AuthorService authorService;
    private static BookService bookService;

    @Autowired
    public DataImporter(AuthorService authorServiceImp, BookService bookServiceImp) {
        authorService = authorServiceImp;
        bookService = bookServiceImp;
    }


    public static List<Book> getAll() throws UnirestException {
        String url = "http://private-anon-0bf0f56faa-bookstore.apiary-mock.com/data/books";

        HttpResponse<JsonNode> response = Unirest.get(url)
                .asJson();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonParser googleJsonParser = new JsonParser();
        JsonElement je = googleJsonParser.parse(response.getBody().toString());

        Parser parser = new Parser(gson);

        JsonArray jsonArray = je.getAsJsonArray();

        List<Book> books = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            BookDTO bookDTO = parser.fromJson(element, BookDTO.class);

            Author authorToAddBook;

            Author found = authorService.findAuthorByName(bookDTO.getAuthor());
            if (found == null) {
                authorToAddBook = authorService.saveAuthor(
                        new Author(bookDTO.getAuthor(), new HashSet<>()));
            } else {
                authorToAddBook = found;
            }

            Book book = new Book();
            book.setAuthor(authorToAddBook);
            book.setTitle(bookDTO.getTitle());
            book.setISBN(bookDTO.getISBN());
            book.setPrice(bookDTO.getPrice().getValue());
            book.setImageUrl(bookDTO.getImage());
            book.setSummary(bookDTO.getSummary().substring(0, 69) + "...");

            Book savedBook = bookService.saveBook(book);

            authorToAddBook.getBooks().add(savedBook);
            authorService.updateAuthor(authorToAddBook);
            books.add(savedBook);
        }
        return books;
    }
}
