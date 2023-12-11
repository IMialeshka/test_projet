package by.mialeshka.effectiveMobile.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CommentTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String description;

    @ManyToOne
    private UserTask author;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Task task;
}
