package freec.softbrain.bookstorage.controller;

import freec.softbrain.bookstorage.model.dto.AuthorDto;
import freec.softbrain.bookstorage.model.entity.Author;
import freec.softbrain.bookstorage.service.impl.AuthorServiceImpl;
import freec.softbrain.bookstorage.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/authors")
public class AuthorApi {
    @Autowired
    private AuthorServiceImpl authorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") int id) {
        Author author = authorService.findById(id);
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not_found", new Throwable("The author with id=" + id + " is not exist."));
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody AuthorDto dto) {
        dto.setId(0);
        authorService.save(MapperUtils.map(dto));
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> update(@RequestBody AuthorDto dto, @PathVariable("id") int id) {
        if (id != dto.getId()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "invalid", new Throwable("The id value is not match with the path variable."));
        }
        Author author = authorService.findById(id);
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not_found", new Throwable("The author with id=" + id + " is not exist."));
        }
        authorService.save(MapperUtils.map(dto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id, @RequestParam(value = "overWarning", required = false) Boolean overWarning) {
        Author author = authorService.findById(id);
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not_found", new Throwable("The author with id=" + id + " is not exist."));
        }
        if (overWarning == null) {
            overWarning = Boolean.FALSE;
        }
        authorService.delete(id, overWarning);
        return ResponseEntity.noContent().build();
    }
}
