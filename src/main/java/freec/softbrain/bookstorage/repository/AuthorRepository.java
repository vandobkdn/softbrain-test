package freec.softbrain.bookstorage.repository;

import freec.softbrain.bookstorage.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("select a from Author a where a.authorId = :authorId and a.removal <> true")
    Optional<Author> findById(@Param("authorId") int authorId);
}
