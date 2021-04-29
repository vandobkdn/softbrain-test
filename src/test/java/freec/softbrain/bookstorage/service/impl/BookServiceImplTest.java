package freec.softbrain.bookstorage.service.impl;

import com.google.common.collect.Lists;
import freec.softbrain.bookstorage.model.dto.BookDto;
import freec.softbrain.bookstorage.model.entity.Author;
import freec.softbrain.bookstorage.model.entity.Book;
import freec.softbrain.bookstorage.repository.BookRepository;
import freec.softbrain.bookstorage.util.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    private BookDto dto;
    private Author author;

    @BeforeEach
    public void setup() {
        dto = new BookDto();
        dto.setId(0);
        dto.setAuthorId(1);
        dto.setCategory("Literature");
        dto.setDescription("The leadership is a best seller for months");
        dto.setPrice(23.4f);
        dto.setTitle("The Wolf");

        author = new Author("Smith Jonhsone");
    }

    @Test
    public void save_test() {
        Book book = MapperUtils.map(dto, author);
        when(bookRepository.save(book)).thenReturn(book);
        bookService.save(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void delete_test() {
        Book book = MapperUtils.map(dto, author);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        bookService.delete(book.getId());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void findOne_test() {
        Book book = MapperUtils.map(dto, author);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        bookService.findById(book.getId());
        assertEquals(0, book.getId());
    }

    @Test
    public void findAll_test_with_result() {
        Book book = MapperUtils.map(dto, author);
        Pageable paging = PageRequest.of(0, 10, Sort.by("id"));
        Page<Book> pageResult = new PageImpl<Book> (List.of(book));
        when(bookRepository.findAll(paging)).thenReturn(pageResult);
        bookService.findAll(0, 10, "id");
        assertEquals(1, pageResult.getContent().size());
    }

    @Test
    public void findAll_test_without_result() {
        Pageable paging = PageRequest.of(0, 10, Sort.by("id"));
        Page<Book> pageResult = new PageImpl<Book> (Lists.newArrayList());
        when(bookRepository.findAll(paging)).thenReturn(pageResult);
        bookService.findAll(0, 10, "id");
        assertEquals(0, pageResult.getContent().size());
    }

    @Test
    public void findByAuthorName_test() {
        Book book = MapperUtils.map(dto, author);
        List<Book> result = List.of(book);
        when(bookRepository.findBooksByAuthorName("Smith Jonhsone")).thenReturn(result);
        bookService.findByAuthorName("Smith Jonhsone");
        assertEquals(1, result.size());
    }

}