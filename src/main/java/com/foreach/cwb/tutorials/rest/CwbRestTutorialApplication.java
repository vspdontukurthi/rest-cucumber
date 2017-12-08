package com.foreach.cwb.tutorials.rest;

import com.foreach.cwb.tutorials.rest.domain.Book;
import com.foreach.cwb.tutorials.rest.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CwbRestTutorialApplication
{
	@Autowired
	public void createBooks( BookRepository bookRepository ) {
		Book jurassicPark = new Book();
		jurassicPark.setAuthor( "Michael Crichton" );
		jurassicPark.setTitle( "Jurassic Park" );

		Book schindlersList = new Book();
		schindlersList.setAuthor( "Thomas Keneally" );
		schindlersList.setTitle( "Schindler's List" );

		Book lowExpectations = new Book();
		lowExpectations.setAuthor( "Unknown" );
		lowExpectations.setTitle( "Not So Great Expectations" );

		bookRepository.save( Arrays.asList( jurassicPark, schindlersList, lowExpectations ) );
	}

	public static void main( String[] args ) {
		SpringApplication.run( CwbRestTutorialApplication.class, args );
	}
}
