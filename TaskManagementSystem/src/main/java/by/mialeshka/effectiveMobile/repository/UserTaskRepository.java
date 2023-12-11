package by.mialeshka.effectiveMobile.repository;

import by.mialeshka.effectiveMobile.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    UserTask findByUsername(String userName);
}
