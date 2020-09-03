package com.geekbrains.book.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookStoreApplication {
	//1. Протестировать работу корзины;
	//2. Протестируйте работу BookRestController.
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
}
