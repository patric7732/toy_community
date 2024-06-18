package org.example.toy_restboard.domain.board.dto;

import lombok.Getter;

@Getter
public class BoardUpdateDto {
    private String title;
    private String content;

    public BoardUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
