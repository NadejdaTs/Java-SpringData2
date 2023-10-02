package advancedQueryingEx.service;

import advancedQueryingEx.model.entity.Book;
import advancedQueryingEx.model.entity.BookSummary;
import advancedQueryingEx.model.entity.EditionType;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllTitlesByAgeRestriction(String ageRestriction);

    List<String> findAllTitlesByEditionAndCopies(EditionType editionType, Integer copies);

    List<Book> findAllPriceNotBetween(float lowerBound, float upperBound);

    List<Book> findNotReleasedIn(int releaseYear);

    List<Book> findBooksReleasedBefore(String date);

    List<String> findAllTitlesContaining(String search);

    List<Book> findByAuthorLastNameStartsWith(String search);

    int countBooksWithTitleLongerThen(int length);

    BookSummary getInformationForTitle(String title);

    int addCopiesToBooksAfter(String date, int amount);

    int deleteWithCopiesLessThan(int amount);
}
