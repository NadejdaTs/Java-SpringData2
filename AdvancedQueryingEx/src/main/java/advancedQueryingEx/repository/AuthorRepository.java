package advancedQueryingEx.repository;

import advancedQueryingEx.model.entity.Author;
import advancedQueryingEx.model.entity.AuthorNamesWithTotalCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /*@Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllByBookSizeDesc();*/

    List<Author> findByFirstNameEndingWith(String endsWith);

    @Query("SELECT a.firstName AS firstName, a.lastName AS lastName, SUM(b.copies) AS totalCopies FROM Author AS a" +
            " JOIN a.books AS b" +
            " GROUP BY b.author" +
            " ORDER BY totalCopies DESC")
    List<AuthorNamesWithTotalCopies> getWithTotalCopies();

    @Query(value = "CALL TOTAL_WRITTEN_BOOKS(:firstName, :lastName);", nativeQuery = true)
    List<String> findTotalBooksByAuthorName(String firstName, String lastName);
}
