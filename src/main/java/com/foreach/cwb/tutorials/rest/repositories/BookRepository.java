package com.foreach.cwb.tutorials.rest.repositories;

import com.foreach.cwb.tutorials.rest.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Arne Vandamme
 */
@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends JpaRepository<Book, Long>
{
}
