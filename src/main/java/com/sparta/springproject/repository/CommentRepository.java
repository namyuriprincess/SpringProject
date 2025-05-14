package com.sparta.springproject.repository;
import com.sparta.springproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findAllByScheduleIdOrderByCreatedAtAsc(Long scheduleId);
}
