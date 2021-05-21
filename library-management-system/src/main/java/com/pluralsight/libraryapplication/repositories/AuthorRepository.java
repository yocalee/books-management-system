package com.pluralsight.libraryapplication.repositories;

import com.pluralsight.libraryapplication.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByBooksId(long bookId);

    @Query("SELECT a from Author a where a.fullName = ?1")
    Author findAuthorByName(String fullName);
}
