package by.prohor.librcrud.repository;

import by.prohor.librcrud.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query("SELECT b from Book b where CONCAT(b.id, b.title, b.author, b.publish) like %?1%")
    public List<Book> findAll(String keyword);
}
