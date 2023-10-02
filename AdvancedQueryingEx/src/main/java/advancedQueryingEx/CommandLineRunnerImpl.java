package advancedQueryingEx;

import advancedQueryingEx.model.entity.Book;
import advancedQueryingEx.model.entity.BookSummary;
import advancedQueryingEx.service.AuthorService;
import advancedQueryingEx.service.BookService;
import advancedQueryingEx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();

//        printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
//        printAllAuthorsAndNumberOfTheirBooks();
//        printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");
        Scanner sc = new Scanner(System.in);

        String title = sc.nextLine();
        BookSummary summary = this.bookService.getInformationForTitle(title);
        System.out.println(summary.getTitle() + " " + summary.getEditionType() + " " + summary.getAgeRestriction() + " " + summary.getPrice());


//        01
        /*String restriction = sc.nextLine();
        this.bookService.findAllTitlesByAgeRestriction(restriction)
                .forEach(System.out::println);*/
//        02
        /*this.bookService.findAllTitlesByEditionAndCopies(EditionType.GOLD, 5000)
                .forEach(System.out::println);*/
//        03
        /*this.bookService.findAllPriceNotBetween(5, 40)
                .forEach(b -> System.out.println(b.getTitle() + " - " + b.getPrice()));*/
//        04
        /*int releaseYear = Integer.parseInt(sc.nextLine());
        this.bookService.findNotReleasedIn(releaseYear)
                .forEach(b -> System.out.println(b.getTitle()));*/
//        05
        /*String date = sc.nextLine();
        this.bookService.findBooksReleasedBefore(date)
                .forEach(b -> System.out.printf("%s %s %.2f%n",
                        b.getTitle(), b.getEditionType(), b.getPrice()));*/
//        06
        /*String endsWith = sc.nextLine();
        this.authorService.findByFirstNameEndingWith(endsWith)
                .stream()
                .map(a -> a.getFirstName() + " " + a.getLastName())
                .forEach(System.out::println);*/
//        07
        /*String search = sc.nextLine();
        this.bookService.findAllTitlesContaining(search)
                .forEach(System.out::println);*/
//        08
        /*String search = sc.nextLine();
        this.bookService.findByAuthorLastNameStartsWith(search)
                .forEach(b -> System.out.printf("%s (%s %s)%n",
                        b.getTitle(),
                        b.getAuthor().getFirstName(),
                        b.getAuthor().getLastName()));*/
//        09
        /*int length = Integer.parseInt(sc.nextLine());
        int totalBooks = this.bookService.countBooksWithTitleLongerThen(length);
        System.out.printf("There are %d books with longer title than %d symbols%n", totalBooks, length);*/
//        10
        /*this.authorService.getWithTotalCopies()
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName() + " - " + a.getTotalCopies()));*/
//        11
        /*String title = sc.nextLine();
        BookSummary summary = this.bookService.getInformationForTitle(title);
        System.out.println(summary.getTitle() + " " + summary.getEditionType() + " " + summary.getAgeRestriction() + " " + summary.getPrice());*/
//        12
        /*String date = sc.nextLine();
        int amount = Integer.parseInt(sc.nextLine());
        int booksUpdated = this.bookService.addCopiesToBooksAfter(date, amount);
        System.out.printf("%d books are released after %s, so total of %d book copies were added%n", booksUpdated, date, amount * booksUpdated);*/
//        13
        /*int amount = Integer.parseInt(sc.nextLine());
        this.bookService.deleteWithCopiesLessThan(amount);*/
//        14
        String authorName = sc.nextLine();
        String firstName = authorName.split(" ")[0];
        String lastName = authorName.split(" ")[1];

        this.authorService.findTotalBooksByAuthorName(firstName, lastName)
                .forEach(a -> System.out.println(a));
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    /*private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }*/

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
