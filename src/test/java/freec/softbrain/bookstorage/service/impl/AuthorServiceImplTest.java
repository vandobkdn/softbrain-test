package freec.softbrain.bookstorage.service.impl;

import com.google.common.collect.Lists;
import freec.softbrain.bookstorage.model.dto.AuthorDto;
import freec.softbrain.bookstorage.model.entity.Author;
import freec.softbrain.bookstorage.model.entity.Book;
import freec.softbrain.bookstorage.repository.AuthorRepository;
import freec.softbrain.bookstorage.util.MapperUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private BookServiceImpl bookService;
    @InjectMocks
    private AuthorServiceImpl authorService;

    private AuthorDto dto;
    private List<Book> books = new ArrayList<>();

    @BeforeEach
    public void setup() {
        dto = new AuthorDto();
        dto.setAuthorName("Smith Jonhson");
        dto.setId(0);

        books.add(new Book());
        books.add(new Book());
    }

    @Test
    public void save_test() {
        Author author = MapperUtils.map(dto);
        when(authorRepository.save(author)).thenReturn(author);
        authorService.save(author);
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void delete_test_without_override() {
        Author author = MapperUtils.map(dto);
        when(authorRepository.save(author)).thenReturn(author);
        when(authorRepository.findById(author.getAuthorId())).thenReturn(Optional.of(author));
        when(bookService.findByAuthorName(author.getAuthorName())).thenReturn(books);

        try {
            authorService.delete(author.getAuthorId(), Boolean.FALSE);
        } catch (Exception e) {
            assertEquals(e.getCause().getMessage(), "This author is have the books, remove the author will remove all the books of the author. " +
                    "Are you sure to remove the Author!!");
        }
    }

    @Test
    public void delete_test_with_override() {
        Author author = MapperUtils.map(dto);
        when(authorRepository.save(author)).thenReturn(author);
        when(authorRepository.findById(author.getAuthorId())).thenReturn(Optional.of(author));
        when(bookService.findByAuthorName(author.getAuthorName())).thenReturn(books);

        authorService.delete(author.getAuthorId(), Boolean.TRUE);
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void findOne_test() {
        Author author = MapperUtils.map(dto);
        when(authorRepository.findById(author.getAuthorId())).thenReturn(Optional.of(author));

        authorService.findById(author.getAuthorId());
        verify(authorRepository, times(1)).findById(author.getAuthorId());
        assertEquals("Smith Jonhson", author.getAuthorName());
    }

    @Test
    public void findAll_test() {
        Author author = MapperUtils.map(dto);
        List<Author> result = Lists.newArrayList(author);
        when(authorRepository.findAll()).thenReturn(Lists.newArrayList(author));

        authorService.findAll();
        verify(authorRepository, times(1)).findAll();
        assertEquals(1, result.size());
    }

}