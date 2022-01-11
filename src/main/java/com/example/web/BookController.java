package com.example.web;

import com.example.persistence.entity.Book;
import com.example.persistence.repo.BookRepository;
import com.example.web.exception.BookIdMismatchException;
import com.example.web.exception.BookNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller has the @put, @post, @delete and @get end points related to the bookRepository.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

  @Autowired
  private BookRepository bookRepository;

  /**
   * Summary.
   *
   * @param book Book object as an input parameter.
   * @return returns the successfully created book object as the responseEntity.
   */
  @PostMapping(value = "/createBook", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Book> create(@RequestBody Book book) {

    try {
      return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    } catch (Exception e) {
      if (book == null || book.getTitle() == null || book.getAuthor() == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      } else {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
  }

  /**
   * Summary.
   *
   * @param id accepts the id for which Book record needs to be fetched.
   * @return returns Book object.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Book> findById(@PathVariable Long id) {
    final Optional<Book> bookOptional = bookRepository.findById(id);
    return bookOptional.map(book -> new ResponseEntity<>(book, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Get object of Book based on the given title.
   *
   * @param title accepts the title for which Book records need to be fetched.
   * @return returns Book object.
  */
  @GetMapping("/title/{title}")
  public List<Book> findByTitle(@PathVariable String title) {
    return bookRepository.findByTitle(title);
  }

  /**
   * Fetches all the Book records.
   *
   * @return returns List of Book object.
   */
  @GetMapping
  public Iterable<Book> findAll() {
    return bookRepository.findAll();
  }

  /**
   * Deleted Book object based on given id.
   *
   * @param id accepts the id for which Book record needs to be deleted.
   */
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    bookRepository.findById(id)
        .orElseThrow(BookNotFoundException::new);
    bookRepository.deleteById(id);
  }

  /**
   * Summary.
   *
   * @param id accepts the id for which Book record needs to be fetched.
   * @return returns Book object.
  */
  @PutMapping("/{id}")
  public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
    if (book.getId() != id) {
      throw new BookIdMismatchException();
    }

    bookRepository.findById(id)
        .orElseThrow(BookNotFoundException::new);
    return bookRepository.save(book);
  }
}
