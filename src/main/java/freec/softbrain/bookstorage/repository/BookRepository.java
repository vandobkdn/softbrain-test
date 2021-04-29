package freec.softbrain.bookstorage.repository;

import freec.softbrain.bookstorage.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select b from Book b where b.author.authorName like :authorName")
    List<Book> findBooksByAuthorName(@Param("authorName") String authorName);

    @Query("select b from Book b where b.id = :bookId and b.removal <> true")
    Optional<Book> findById(@Param("bookId") int bookId);
}
