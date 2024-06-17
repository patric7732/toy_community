package org.example.toy_restboard.domain.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.toy_restboard.global.entity.BaseEntity;
import org.example.toy_restboard.domain.comment.entity.Comment;
import org.example.toy_restboard.domain.user.entity.User;

import java.util.List;

@Table(name = "boards")
@Entity
@Getter
@Setter
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    private String content;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

}
