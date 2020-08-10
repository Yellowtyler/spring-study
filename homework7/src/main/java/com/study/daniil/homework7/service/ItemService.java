package com.study.daniil.homework7.service;


import com.study.daniil.homework7.exceptions.ItemNotFoundException;
import com.study.daniil.homework7.model.Item;
import com.study.daniil.homework7.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item findItemById(Long id) {
       return itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException("Товар с id " + id + " не найден!"));
    }

    public List<Item> findAllItems() {
       return itemRepository.findAll();
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public void removeItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
