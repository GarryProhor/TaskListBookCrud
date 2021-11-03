package by.prohor.librcrud.services;


import by.prohor.librcrud.exceptions.LibraryNotFoundException;
import by.prohor.librcrud.model.Newspaper;
import by.prohor.librcrud.repository.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewspaperService {
    @Autowired
    private NewspaperRepository repository;

    public List<Newspaper> listAll(String keyword){
        if(keyword != null){
            return repository.findAll(keyword);
        }
        return (List<Newspaper>) repository.findAll();
    }
    public void save(Newspaper newspaper) {
        repository.save(newspaper);
    }

    public Newspaper get(Integer id) throws LibraryNotFoundException {
        Optional<Newspaper> result = repository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new LibraryNotFoundException("Could not find any books ID" + id);
    }

    public void delete(Integer id) throws LibraryNotFoundException {
        repository.deleteById(id);
    }
}
