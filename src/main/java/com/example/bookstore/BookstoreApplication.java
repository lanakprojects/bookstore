package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(BookRepository repository, CategoryRepository catrepository) {
		return (args) -> {
			log.info("insert example data");
			
			catrepository.save(new Category("novel"));
			catrepository.save(new Category("fantasy"));
			catrepository.save(new Category("detective"));
			
			repository.save(new Book("Pride and Prejudice", "Jane Austen", 1813, "book1813", 18.13, catrepository.findByName("novel").get(0)));
			repository.save(new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", 1967, "book1967", 1967, catrepository.findByName("fantasy").get(0)));
			repository.save(new Book("The Flowers of Evil", "Charles Baudelaire", 1857, "book1857", 1857, catrepository.findByName("novel").get(0)));
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
