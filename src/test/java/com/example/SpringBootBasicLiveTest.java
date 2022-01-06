package com.example;

import com.example.persistence.entity.Book;
import io.restassured.RestAssured;
import io.restassured.response.Response;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpringBootBasicLiveTest {

    private static final String API_ROOT = "http://localhost:8080/api/books";

    @Test
    public void whenGetAllBooks_thenOK() {
        final Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetBooksByTitle_thenOK() {
        final Book book = createRandomBook();
        createBookAsUri(book);

        final Response response = RestAssured.get(API_ROOT + "/title/" + book.getTitle());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.as(List.class)
                .size() > 0);
    }

    private Book createRandomBook() {
        final Book book = new Book();
        book.setTitle(randomAlphabetic(10));
        book.setAuthor(randomAlphabetic(15));
        return book;
    }

    private String createBookAsUri(Book book) {
        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath()
                .get("id");
    }
}
