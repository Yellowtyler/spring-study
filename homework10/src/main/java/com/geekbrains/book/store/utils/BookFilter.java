package com.geekbrains.book.store.utils;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Genre;
import com.geekbrains.book.store.repositories.specifications.BookSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class BookFilter {
    private Specification<Book> spec;
    private String filterParams;

    public BookFilter(Map<String, String> params) {
        params.entrySet().removeIf(e->e.getValue().isEmpty());
        StringBuilder stringBuilder = new StringBuilder("");
        for (Map.Entry<String, String> entry: params.entrySet()) {
            stringBuilder.append("&");
            stringBuilder.append(entry.getKey()+"="+entry.getValue());
        }
        filterParams = stringBuilder.toString();

        spec = Specification.where(null);
        for (Enum<Genre> g : Genre.values()) {
            if (params.containsKey(g.name()) && params.get(g.name()).equals("true")){
                spec = spec.or(BookSpecifications.genreEqual(g));
            }
        }
        if (params.containsKey("maxPrice") ) {
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
