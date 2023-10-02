package springdata8;

import springdata8.entities.Author;
import springdata8.entities.Book;
import springdata8.repositories.AuthorRepository;
import springdata8.repositories.BookRepository;
import springdata8.services.SeedService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    /*private void booksAfter2000_01() {
        LocalDate year2000 = LocalDate.of(2000,12,31);
        List<Book> books = bookRepository.findByReleaseDateAfter(year2000);
        books.forEach(b -> System.out.println(b.getReleaseDate() + " - " + b.getTitle()));
        int count = this.bookRepository.countByReleaseDateAfter(year2000);
        System.out.println("Total count: " + count);
    }*/

    /*private void allAuthorsWithBookBefore1990_02() {
        LocalDate year1990 = LocalDate.of(1999,1,1);
        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);
        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }*/

    //@Transactional -> remove EAGER in Author
    /*private void allAuthorsOrderByBookCount_03() {
        List<Author> authors = this.authorRepository.findAll();
        authors.stream()
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(a -> System.out.printf("%s %s -> %d%n",
                        a.getFirstName(),
                        a.getLastName(),
                        a.getBooks().size()));
        //.sorted(Comparator.comparingInt(c -> c.getBooks().size()))
    }*/

    private void allBooksFromCurrAuthorOrderBy_04() {
        String firstName = "George";
        String lastName = "Powell";
        Author author = this.authorRepository.findByFirstNameAndLastName(firstName, lastName);
        List<Book> books = this.bookRepository.findByAuthorOrderByReleaseDateDescTitleAsc(author);
        //OrderByReleaseDateDesc

        books.stream()
                .forEach(b -> System.out.printf("%s %s %d%n", b.getTitle(), b.getReleaseDate(), b.getCopies()));
    }

    @Override
    public void run(String... args) throws Exception {
        //System.out.println("starting...");
//        this.seedService.seedAuthors();
//        this.seedService.seedCategories();
//        this.seedService.seedBooks();

//        this.seedService.seedAll();
//        this.booksAfter2000_01();
//        this.allAuthorsWithBookBefore1990_02();
//        this.allAuthorsOrderByBookCount_03();
        this.allBooksFromCurrAuthorOrderBy_04();
    }
}
