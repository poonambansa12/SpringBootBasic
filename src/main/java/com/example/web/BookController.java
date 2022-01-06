package com.example.web;

import com.example.persistence.entity.Book;
import com.example.persistence.repo.BookRepository;
import com.example.web.exception.BookIdMismatchException;
import com.example.web.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/createBook")
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    @GetMapping("/title/{title}")
    public List<Book> findByTitle(@PathVariable String title) {
        return bookRepository.findByTitle(title);
    }

    @GetMapping
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if(book.getId() != id) {
            throw new BookIdMismatchException();
        }

        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }
}
