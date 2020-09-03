package com.geekbrains.book.store.soap;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.soap.book.GetAllBooksRequest;
import com.geekbrains.book.store.soap.book.GetBookRequest;
import com.geekbrains.book.store.soap.book.GetBookResponse;
import lombok.AllArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@AllArgsConstructor
public class BookEndpoint {
    public final String namespaceUrl = "http://geekbrains.com/book/store/soap/book";

    private final BookService bookService;

    @PayloadRoot(namespace = namespaceUrl, localPart = "getAllBooksRequest")
    @ResponsePayload
    public GetBookResponse getAllBooks(@RequestPayload GetAllBooksRequest booksRequest) {
        GetBookResponse getBookResponse = new GetBookResponse();

        if (booksRequest.getName().equals("all")){
            for (Book book : bookService.findAll()){
                com.geekbrains.book.store.soap.book.Book soapBook = new com.geekbrains.book.store.soap.book.Book();
                soapBook.setTitle(book.getTitle());
                soapBook.setDescription(book.getDescription());
                soapBook.setPrice(book.getPrice());
                soapBook.setPublishYear(book.getPublishYear());
                getBookResponse.getBook().add(soapBook);
            }
        }

        return getBookResponse;
    }

    @PayloadRoot(namespace = namespaceUrl, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBook(@RequestPayload GetBookRequest booksRequest) {
        GetBookResponse getBookResponse = new GetBookResponse();
        Book book = bookService.findById(booksRequest.getId());
        com.geekbrains.book.store.soap.book.Book soapBook = new com.geekbrains.book.store.soap.book.Book();
        soapBook.setTitle(book.getTitle());
        soapBook.setDescription(book.getDescription());
        soapBook.setPrice(book.getPrice());
        soapBook.setPublishYear(book.getPublishYear());
        getBookResponse.getBook().add(soapBook);
        return getBookResponse;
    }
}
