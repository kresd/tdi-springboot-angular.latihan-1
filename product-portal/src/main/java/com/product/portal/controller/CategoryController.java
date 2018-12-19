package com.product.portal.controller;

import com.product.portal.model.Category;
import com.product.portal.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class CategoryController {

    @Autowired
    CategoryRepository repository;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        System.out.println("Get all Category...");

        List<Category> categories = new ArrayList<>();
        repository.findAll().forEach(categories::add);

        return categories;
    }
}
