package by.awesome.sup.repository;

import by.awesome.sup.entity.common.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
