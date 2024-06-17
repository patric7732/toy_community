package org.example.toy_restboard.domain.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.toy_restboard.domain.board.entity.Board;
import org.example.toy_restboard.global.entity.BaseEntity;

@Table(name = "comments")
@Entity
@Getter
@Setter
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(name = "user_id", nullable = false)
    private String userId;

    private String content;
}
