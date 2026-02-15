package com.restapi.library.controller;

import com.restapi.library.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    private List<Book> books = new ArrayList<>();
    private Long nextId = 26971L;

    public BookController() {
        books.add(new Book(26965L, "Spring Boot Mastery", "Irakoze Micomyiza Rodrigue", "978-2696500001", 2024));
        books.add(new Book(26966L, "The Pragmatic Programmer", "Andrew Hunt", "978-0201616224", 1999));
        books.add(new Book(26967L, "Head First Design Patterns", "Eric Freeman", "978-0596007126", 2004));
        books.add(new Book(26968L, "Code Complete", "Steve McConnell", "978-0735619678", 2004));
        books.add(new Book(26969L, "Refactoring", "Martin Fowler", "978-0134757599", 2018));
        books.add(new Book(26970L, "You Don't Know JS", "Kyle Simpson", "978-1491904244", 2015));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String title) {
        List<Book> result = books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        book.setId(nextId++);
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean removed = books.removeIf(book -> book.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
