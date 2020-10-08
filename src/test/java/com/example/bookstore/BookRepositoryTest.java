package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	 @Autowired
	    private BookRepository repository;
	 
	 @Test
	    public void findByTitleShouldReturnBook() {
	        List<Book> books = repository.findByTitle("The Flowers of Evil");
	        assertThat(books).hasSize(1);
	        assertThat(books.get(0).getTitle()).isEqualTo("The Flowers of Evil");
	    }
	 
	 @Test
	    public void creatingNewBook() {
	    	Book book = new Book("One Flew Over the Cuckoo's Nest", "Ken Kesey", 1963, "book1963", 19.63, new Category("psycology"));
	    	repository.save(book);
	    	assertThat(book.getBookId()).isNotNull();
	    }
	 
	 @Test
		 public void deletingBook() {
		 	List<Book> books = repository.findByTitle("Pride and Prejudice");
		 	long delId = books.get(0).getBookId();
	    	repository.deleteById(delId);
	    	assertThat(repository.findById(delId)).isEmpty();
		 }
	
}
