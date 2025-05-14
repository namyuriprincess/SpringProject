package com.sparta.springproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "conmment")
public class Comment extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long writerId;

    @Column(nullable = false)
    private Long scheduleId;

    @Column
    private Long parent_comment_id;


    public void updateContent(String content) {
        this.content = content;
    }

    public Comment(String content, Long writerId, Long scheduleId, Long parent_comment_id) {
        this.content = content;
        this.writerId = writerId;
        this.scheduleId = scheduleId;
        this.parent_comment_id = parent_comment_id;

    }


}
