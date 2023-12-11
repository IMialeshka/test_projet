package by.mialeshka.effectiveMobile.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    private String name;

    private String password;

    private Date lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "author")
    private List<CommentTask> commentList;

    @OneToMany(mappedBy = "author")
    private List<Task> autTaskList;

    @OneToMany(mappedBy = "performer")
    private List<Task> perTaskList;
}
