package advancedQueryingEx.service;

import advancedQueryingEx.model.entity.Author;
import advancedQueryingEx.model.entity.AuthorNamesWithTotalCopies;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<Author> findByFirstNameEndingWith(String endsWith);

    List<AuthorNamesWithTotalCopies> getWithTotalCopies();
}
