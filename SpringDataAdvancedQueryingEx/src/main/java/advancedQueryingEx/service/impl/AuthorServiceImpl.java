package advancedQueryingEx.service.impl;

import advancedQueryingEx.model.entity.Author;
import advancedQueryingEx.model.entity.AuthorNamesWithTotalCopies;
import advancedQueryingEx.repository.AuthorRepository;
import advancedQueryingEx.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHORS_FILE_PATH = "src/main/resources/files/authors.txt";

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (authorRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .forEach(row -> {
                    String[] fullName = row.split("\\s+");
                    Author author = new Author(fullName[0], fullName[1]);
                    authorRepository.save(author);
                });
    }

    @Override
    public Author getRandomAuthor() {
        long randomId = ThreadLocalRandom
                .current().nextLong(1,
                        authorRepository.count() + 1);

        return authorRepository
                .findById(randomId)
                .orElse(null);
    }

    /*@Override
    public List<String> getAllAuthorsOrderByCountOfTheirBooks() {
        return this.authorRepository
                .findAllByBookSizeDesc()
                .stream()
                .map(author -> String.format("%s %s %d",
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBooks().size()))
                .collect(Collectors.toList());
    }*/

    @Override
    public List<Author> findByFirstNameEndingWith(String endsWith) {
        return this.authorRepository.findByFirstNameEndingWith(endsWith);
    }

    @Override
    public List<AuthorNamesWithTotalCopies> getWithTotalCopies() {
        return this.authorRepository.getWithTotalCopies();
    }

    @Override
    public List<String> findTotalBooksByAuthorName(String firstName, String lastName) {
        return this.authorRepository.findTotalBooksByAuthorName(firstName, lastName);
    }
}
