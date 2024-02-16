package com.software.lamia.bootstrap;

import com.software.lamia.domain.Book;
import com.software.lamia.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();
        Book bookDDD = new Book("Domian Driven Design", "123", "RandomHouse", null);
        Book savedDDD = bookRepository.save(bookDDD);
        Book bookSIA = new Book("Spring In Action", "2323213", "Oriely", null);
        Book savedSIA = bookRepository.save(bookSIA);
        bookRepository.findAll().forEach(book -> {
            System.out.println("Book Id: " + book.getId());
            System.out.println("Book Title: " + book.getTitle());
        });
     }
}
