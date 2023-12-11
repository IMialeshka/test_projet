package by.mialeshka.effectiveMobile.repository;

import by.mialeshka.effectiveMobile.entity.CommentTask;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentTaskRepository extends JpaRepository<CommentTask, Long> {
    @Query("select b from CommentTask as b where b.task.id = :id order by b.id")
    List<CommentTask> getCommentTaskByTaskId(@Param("id") long id, Pageable pageable);
    @Query("select b from CommentTask as b where b.author.id = :id order by b.id")
    List<CommentTask> getCommentTaskByAuthorId(@Param("id") long id, Pageable pageable);

    @Query("select b from CommentTask as b where b.task.id = :id")
    List<CommentTask> getAllByTaskIdFullList(@Param("id") long id);

}
