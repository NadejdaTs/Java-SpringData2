package com.example.springdata8.repositories;

import com.example.springdata8.entities.Author;
import com.example.springdata8.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);

//    List<Author> findAllOrderByBooksCount();
//    List<Author> findAllOrderByBooksCountDesc();
//    List<Author> findAll();
    Author findByFirstNameAndLastName(String firstName, String lastName);
}
