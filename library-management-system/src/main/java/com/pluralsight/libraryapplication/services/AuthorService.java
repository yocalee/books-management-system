package com.pluralsight.libraryapplication.services;

import com.pluralsight.libraryapplication.models.Author;
import com.pluralsight.libraryapplication.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findAuthorById(long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()) {
            throw new InvalidParameterException("Invalid author id.");
        }
        return author.get();
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author deleteAuthor(long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()){
            throw new InvalidParameterException("Invalid author id.");
        }
        authorRepository.delete(author.get());
        return author.get();
    }

    public Author findByBookId(long bookId) {
        Optional<Author> author = authorRepository.findByBooksId(bookId);
        if (!author.isPresent()) {
            throw new InvalidParameterException("Invalid author id.");
        }
        return author.get();
    }

    public Author findAuthorByName(String fullName) {
        return authorRepository.findAuthorByName(fullName);
    }

}
