package com.geekbrains.book.store.utils;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.repositories.specifications.BookSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class BookFilter {
    private Specification<Book> spec;
    private String filterParams;

    public BookFilter(Map<String, String> params) {
        spec = Specification.where(null);
        if (params.containsKey("maxPrice")) {
            spec = spec.and(BookSpecifications.priceLesserOrEqualsThan(Integer.parseInt(params.get("maxPrice"))));
        }
        if (params.containsKey("minPrice")) {
            spec = spec.and(BookSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(params.get("minPrice"))));
        }
        if (params.containsKey("titlePart")) {
            spec = spec.and(BookSpecifications.titleLike(params.get("titlePart")));
        }

    }
}
