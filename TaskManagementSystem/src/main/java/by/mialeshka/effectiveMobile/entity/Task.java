package by.mialeshka.effectiveMobile.entity;

import by.mialeshka.effectiveMobile.references.Priority;
import by.mialeshka.effectiveMobile.references.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String heading;


    private String description;


    @Enumerated(EnumType.STRING)
    private Status status;


    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    private UserTask author;

    @ManyToOne
    private UserTask performer;

    @OneToMany(mappedBy = "task")
    private List<CommentTask> listComments;
}
