package by.prohor.librcrud.services;

import by.prohor.librcrud.model.Book;
import by.prohor.librcrud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired private BookRepository repository;

    public List<Book> listAll(){
        return (List<Book>) repository.findAll();
    }

    public void save(Book book) {
        repository.save(book);
    }
}
