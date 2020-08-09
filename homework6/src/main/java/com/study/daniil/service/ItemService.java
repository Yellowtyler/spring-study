package com.study.daniil.service;

import com.study.daniil.exceptions.ItemNotFoundException;
import com.study.daniil.model.Item;
import com.study.daniil.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item findItemById(Long id) throws ItemNotFoundException {
        Item item = itemRepository.findById(id);
        if(item == null) {
            throw new ItemNotFoundException("Товар с id " + id + " не найден!");
        }
        return item;
    }

    public List<Item> findAllItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    public void saveItem(Item item) {
        itemRepository.save(item);
    }
}
