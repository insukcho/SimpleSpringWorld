package com.chris.repository;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.chris.TechtrialApplication;
import com.chris.entity.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TechtrialApplication.class)
@WebAppConfiguration
public class BookRepositoryTest {

	@Autowired
	protected BookRepository bookRepository;

	@Test
	public void books() {
		Iterable<Book> findAll = bookRepository.findAll();

		Book firstBook = findAll.iterator().next();
		
		assertThat(firstBook.getIsbn(), equalTo(11));
		assertThat(firstBook.getAuthor(), equalTo("Chris Cho"));
	}

}
