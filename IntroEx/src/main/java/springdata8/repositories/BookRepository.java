package springdata8.repositories;

import springdata8.entities.Author;
import springdata8.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByReleaseDateAfter(LocalDate releaseDate);
    int countByReleaseDateAfter(LocalDate releaseDate);
    List<Book> findByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
