package com.geekbrains.book.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class BookRestControllerTest {

    private static final String URL = "/api/v1/books/";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private Book book;

    @BeforeEach
    public void init() {
        book = new Book(1L,"Harry Potter 1", "Description 1", new BigDecimal("300"), 2000, Book.Genre.FANTASY);
    }

    @Test
    public void getAllBooksTest() throws Exception {
        List<Book> list = new ArrayList<>();
        list.add(book);
        list.add(new Book(2L, "Harry Potter 2", "Description 2", new BigDecimal("400"), 2001, Book.Genre.FANTASY));
        given(this.bookService.findAll())
                .willReturn(list);
        this.mvc.perform(get(URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Harry Potter 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Harry Potter 2"));
    }

    @Test
    public void getBookByIdTest() throws Exception {
        given(this.bookService.findById(1L))
                .willReturn(book);
        this.mvc.perform(get(URL + "{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Harry Potter 1"));
    }

    @Test
    public void addNewBookTest() throws Exception {
        given(this.bookService.saveOrUpdate(Mockito.any())).willReturn(book);
        this.mvc.perform(post(URL)
                .content(objectMapper.writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(book)));
    }

    @Test
    public void editBookTest() throws Exception {
        given(this.bookService.saveOrUpdate(Mockito.any())).willReturn(book);
        given(this.bookService.existsById(book.getId())).willReturn(true);
        this.mvc.perform(put(URL)
                .content(objectMapper.writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(book)));
    }
}