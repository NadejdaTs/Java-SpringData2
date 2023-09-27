package com.example.springdata8.services;

import com.example.springdata8.entities.Author;
import com.example.springdata8.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long size = this.authorRepository.count();
        //Very important 1!!! - ids are in [0..9] and we must to shift them in [1..10]
        int authorId = new Random().nextInt((int) size) + 1;
        return this.authorRepository.findById(authorId).get();
    }
}
