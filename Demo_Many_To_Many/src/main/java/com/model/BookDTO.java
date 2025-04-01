package com.model;


import lombok.Data;
import java.util.Set;


public class BookDTO {

    private Long id;
    private String name;
    private Set<String> authors;    // ✅ Sirf author names store kar rahe hain

    public BookDTO(Long id, String name, Set<String> authors) {
        this.id = id;
        this.name = name;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }
}

