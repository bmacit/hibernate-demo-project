package com.software.lamia;

import com.software.lamia.domain.Book;
import com.software.lamia.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.software.lamia.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringBootJpaTestSlice {

    @Autowired
    BookRepository bookRepository;

    //@Rollback(value = false)
    @Commit
    @Order(1)
    @Test
    void testJpaTestsSplice() {
        long count = bookRepository.count();
        assertThat(count).isEqualTo(2);
        bookRepository.save(new Book("My Book", "1232", "Self", null));
        long countAfter = bookRepository.count();
        assertThat(count).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestsSpliceTransaction() {
        long count = bookRepository.count();
        assertThat(count).isEqualTo(3);
    }
}
