package org.example.toy_restboard.domain.dto.boarddto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class BoardCreateDto {
    private String userId;
    private String title;
    private String content;

    public BoardCreateDto(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
