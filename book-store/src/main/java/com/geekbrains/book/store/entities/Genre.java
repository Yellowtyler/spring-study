package com.geekbrains.book.store.entities;

import lombok.Getter;

@Getter
public enum Genre {
    FANTASY("фэнтези"), FANTASTIC("фантастика"), DETECTIVE("детектив");

    private String str;

    Genre(String str) {
        this.str=str;
    }
}
