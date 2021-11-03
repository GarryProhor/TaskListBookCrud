package by.prohor.librcrud.repository;

import by.prohor.librcrud.model.Book;
import by.prohor.librcrud.model.Newspaper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewspaperRepository extends CrudRepository<Newspaper, Integer> {

    @Query("SELECT n from Newspaper n where concat(n.id, n.title, n.theme, n.territory, n.periodicity) like %?1%")
    public List<Newspaper> findAll(String keyword);
}
