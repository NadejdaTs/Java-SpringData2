package com.example.springdata8.services;

import com.example.springdata8.entities.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
