package com.lynas.reactivespringjava;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Book {

    @Id
    public String id;
    public String name;
    public String authorId;

    public Book(String name, String authorId) {
        this.name = name;
        this.authorId = authorId;
    }
}

