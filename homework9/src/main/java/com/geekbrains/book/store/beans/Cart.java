package com.geekbrains.book.store.beans;

import com.geekbrains.book.store.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<Book> books = new ArrayList<>();
}
