package springdata8.services;

import springdata8.entities.*;
import springdata8.repositories.BookRepository;
import springdata8.repositories.CategoryRepository;
import springdata8.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private static final String RESOURCE_PATH = "src/main/resources/files";
    private static final String AUTHORS_FILE_NAME = RESOURCE_PATH + "/authors.txt";
    private static final String CATEGORIES_FILE_NAME = RESOURCE_PATH + "/categories.txt";
    private static final String BOOKS_FILE_NAME = RESOURCE_PATH + "/books.txt";

    @Autowired
    //create AuthorRepository extends JpaRepository<Author, Integer>
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService autoservice;

    @Autowired
    private CategoryService categoryService;
    @Override
    public void seedAuthors() throws IOException {
        //May be will be a problem with larger file
        //Take whole path from C:
        Path fullPath = Path.of(AUTHORS_FILE_NAME).toAbsolutePath();
        Files.readAllLines(fullPath)
                .stream()
                .filter(s -> !s.isBlank())
                .map(s -> s.split(" "))
                .map(names -> new Author(names[0], names[1]))
                .forEach(authorRepository::save);
    }

    @Override
    public void seedCategories() throws IOException {
        Path fullPath = Path.of(CATEGORIES_FILE_NAME).toAbsolutePath();
        Files.readAllLines(fullPath)
                .stream()
                .filter(s -> !s.isBlank())
                .map(Category::new)
                .forEach(categoryRepository::save);
    }

    @Override
    public void seedBooks() throws IOException {
        Path fullPath = Path.of(BOOKS_FILE_NAME).toAbsolutePath();
        Files.readAllLines(fullPath)
                .stream()
                .filter(s -> !s.isBlank())
                .map(this::getBookObject)
                .forEach(bookRepository::save);
    }

    private Book getBookObject(String line){
        String[] booksParts = line.split(" ");
        int editionTypeIndex = Integer.parseInt(booksParts[0]);

        EditionType editionType = EditionType.values()[editionTypeIndex];
        LocalDate publishDate = LocalDate.parse(booksParts[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        int copies = Integer.parseInt(booksParts[2]);
        BigDecimal price = new BigDecimal(booksParts[3]);
        int ageRestrictionIndex = Integer.parseInt(booksParts[4]);
        AgeRestriction ageRestriction = AgeRestriction.values()[ageRestrictionIndex];

        String title = Arrays.stream(booksParts)
                .skip(5)
                .collect(Collectors.joining(" "));
        Author author = autoservice.getRandomAuthor();
        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(title, editionType, price, copies, publishDate, ageRestriction, author, categories);
    }


}
