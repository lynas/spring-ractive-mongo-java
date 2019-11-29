package com.lynas.reactivespringjava;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
@Log4j2
public class BookController {
    private final ReactiveBookRepository bookRepository;
    private final ReactiveAuthorRepository authorRepository;

    @GetMapping
    public Publisher<Book> getAll() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Publisher<Book> addNew(@RequestBody Book book) {
        Book saveBook = new Book(book.name, book.authorId);
        return bookRepository.save(saveBook);
    }

    @GetMapping("/author-name/{authorName}")
    public Publisher<Book> getBooksByAuthor(@PathVariable String authorName) {
        /*Mono<Author> authorMono = authorRepository.findByName(Mono.just(authorName));
        Mono<String> authorIdMono = authorMono.map(author -> author.id);
        Flux<Book> bookFlux = bookRepository.findByAuthorId(authorIdMono);
        return bookFlux;*/

        return authorRepository.findByName(authorName)
                .flatMapMany(author -> bookRepository.findByAuthorId(author.id));
    }

}
