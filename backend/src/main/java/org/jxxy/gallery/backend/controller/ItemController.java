package org.jxxy.gallery.backend.controller;

import org.jxxy.gallery.backend.domain.ItemEntity;
import org.jxxy.gallery.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/api/items")
    public List<ItemEntity> getItems() {
        List<ItemEntity> items = itemRepository.findAll();
        return items;
    }
}
