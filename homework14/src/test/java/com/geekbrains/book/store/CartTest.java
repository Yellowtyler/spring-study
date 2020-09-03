package com.geekbrains.book.store;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

@SpringBootTest
class CartTest {
    @Autowired
    private Cart cart;

    @MockBean
    private BookService bookService;

    private Book book;

    @BeforeEach
    public void init() {
        book = new Book(1L,"Harry Potter 1", "Description 1", new BigDecimal("300"), 2000, Book.Genre.FANTASY);
    }

    @Test
    public void AddCartTest(){
        Mockito.doReturn(book)
                .when(bookService)
                .findById(1L);

        cart.add(book);

        Assertions.assertEquals(1, cart.getItems().size());
    }
}