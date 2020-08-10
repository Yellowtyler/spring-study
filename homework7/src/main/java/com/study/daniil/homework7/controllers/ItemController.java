package com.study.daniil.homework7.controllers;


import com.study.daniil.homework7.exceptions.ItemNotFoundException;
import com.study.daniil.homework7.model.Item;
import com.study.daniil.homework7.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/all")
    public String getAllItems(Model model) {
        model.addAttribute("items", itemService.findAllItems());
        return "all_items";
    }

    @PostMapping("/add")
    public String saveItem(@ModelAttribute Item item) {
        itemService.saveItem(item);
        return "redirect:/items/all";
    }

    @GetMapping("/remove/{id}")
    public String removeItem(@PathVariable Long id) {
        itemService.removeItemById(id);
        return "redirect:/items/all";
    }

    @GetMapping("/edit/{id}")
    public String getEditItem(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemService.findItemById(id));
        return "item-edit-page";
    }

    @PostMapping("/edit")
    public String editItem(@ModelAttribute Item item) {
        itemService.saveItem(item);
        return "redirect:/items/all";
    }


}
