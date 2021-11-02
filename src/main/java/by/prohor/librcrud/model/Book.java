package by.prohor.librcrud.model;


import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "title")
    private String title;
    @Column(nullable = false, name = "author")
    private String author;
    @Column(nullable = false,  name = "publish")
    private String publish;



}
