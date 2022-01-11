package com.example.persistence.repo;

import com.example.persistence.entity.Book;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to perform crud operations on Book object.
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
  List<Book> findByTitle(String title);
}
