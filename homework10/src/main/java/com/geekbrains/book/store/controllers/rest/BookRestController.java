package com.geekbrains.book.store.controllers.rest;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Genre;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.utils.BookFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


//21ого августа утром-днем доделаю дз. Извиняюсь, что скинул недоделанное дз.
@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookRestController {
    private BookService bookService;

    @GetMapping("/show")
    public Map showAllBooks(@RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
                            @RequestParam Map<String, String> params,
                            @RequestParam(name = "gen", required = false) String genre
    ) {
        BookFilter bookFilter = new BookFilter(params);
        Page<Book> page = bookService.findAll(bookFilter.getSpec(), pageIndex - 1, 5);
        Map<String, Object> map = new HashMap<>();
        map.put("books", page.getContent());
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            map.put("pageNumbers", pageNumbers);
            map.put("pageIndex", pageIndex);
            map.put("genres", Genre.values());
            map.put("strParams", bookFilter.getFilterParams());
        }
        return map;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Book addBook(@RequestBody Book book) {
        if (book.getId() != null) {
            book.setId(null);
        }
        return bookService.saveOrUpdate(book);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Book editBook(@RequestBody Book book) {
        if (!bookService.existsById(book.getId())) {
            throw new ResourceNotFoundException("Book with id: " + book.getId() + " doesn't exists");
        }
        return bookService.saveOrUpdate(book);
    }

    @DeleteMapping
    public void deleteAllBooks() {
        bookService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
