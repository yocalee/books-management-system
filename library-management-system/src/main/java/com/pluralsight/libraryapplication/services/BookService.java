package com.pluralsight.libraryapplication.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.pluralsight.libraryapplication.models.Author;
import com.pluralsight.libraryapplication.models.Book;
import com.pluralsight.libraryapplication.repositories.BookRepository;
import com.pluralsight.libraryapplication.util.DataImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return this.bookRepository.findAll();
    }

    public Book findById(long id) {
        Optional<Book> book = this.bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new InvalidParameterException("Invalid book id.");
        }
        return book.get();
    }

    public List<Book> findByAuthorId(long id) {
        return this.bookRepository.findAllByAuthorId(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public Book deleteBook(long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new InvalidParameterException("Invalid book id.");
        }
        bookRepository.delete(book.get());
        return book.get();
    }

    public List<Book> findBookByAuthorName(String name) {
        return findAllBooks().stream().filter(b -> b.getAuthor().getFullName().equals(name))
                .collect(Collectors.toList());
    }

    public void importAll() throws UnirestException {
        DataImporter.getAll();
    }

}
