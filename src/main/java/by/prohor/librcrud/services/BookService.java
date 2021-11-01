package by.prohor.librcrud.services;

import by.prohor.librcrud.exceptions.BookNotFoundException;
import by.prohor.librcrud.model.Book;
import by.prohor.librcrud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired private  BookRepository repository;


    public List<Book> listAll(){
        return (List<Book>) repository.findAll();
    }

    public void save(Book book) {
        repository.save(book);
    }

    public Book get(Integer id) throws BookNotFoundException {
        Optional<Book> result = repository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new BookNotFoundException("Could not find any books ID" + id);
    }

    public void delete(Integer id) throws BookNotFoundException {
        Long count = repository.countById(id);
        if(count == null || count == 0){
            throw new BookNotFoundException("Could not find any books ID" + id);
        }
        repository.deleteById(id);
    }
}
