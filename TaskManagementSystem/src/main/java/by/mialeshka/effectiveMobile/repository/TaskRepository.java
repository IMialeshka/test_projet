package by.mialeshka.effectiveMobile.repository;

import by.mialeshka.effectiveMobile.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select b from Task as b where b.author.id = :id order by b.id")
    List<Task> getTaskListByAuthorId(@Param("id") Long id, Pageable pageable);

    @Query("select b from Task as b where b.performer.id= :id order by b.id")
    List<Task> getTaskListByPerformerId(@Param("id") Long id, Pageable pageable);

}
