package by.prohor.librcrud.model;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "newspaper")
public class Newspaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "theme")
    private String theme;

    @Column(nullable = false, name = "territory")
    private String territory;

    @Column(nullable = false, name = "periodicity")
    private String periodicity;

}
