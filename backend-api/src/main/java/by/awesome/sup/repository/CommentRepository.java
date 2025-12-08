package by.awesome.sup.repository;

import by.awesome.sup.entity.common.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
