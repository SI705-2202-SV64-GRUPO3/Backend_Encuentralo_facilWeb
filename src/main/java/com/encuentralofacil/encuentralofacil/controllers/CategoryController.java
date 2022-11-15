package com.encuentralofacil.encuentralofacil.controllers;

import com.encuentralofacil.encuentralofacil.entities.Category;
import com.encuentralofacil.encuentralofacil.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin
@RequestMapping("/categories")
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity getAllCategories() {
        List<Category> categories = this.categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}
