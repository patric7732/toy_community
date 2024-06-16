package org.example.toy_restboard.domain.dto.boarddto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDto {
    private Long boardId;
    private String userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BoardDto(Long boardId, String userId, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.boardId = boardId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}