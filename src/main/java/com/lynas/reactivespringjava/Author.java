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
public class Author {

    @Id
    public String id;
    public String name;

    public Author(String name) {
        this.name = name;
    }
}

