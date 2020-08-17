package com.geekbrains.book.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookStoreApplication {
	// Домашнее задание:
	// 1. Добавить корзину, корзина должна быть сессионным бином;
	// @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	// Добавить возможность добавлять книги в корзину
	// Сделать страницу с отображением книг в корзине
	// 2. Книги в корзине должны группироваться, если пользователь
	// захотел купить несколько экземпляров одной книги (книг у нас бесконечно много). Группируйте
	// такие элементы в OrderItem(book_id, count, price)
	// 3. При оформлении заказа, в базе данных должен появиться этот заказ.
	// Заказ состоит из OrderItem и привязывается к пользователю

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
}
