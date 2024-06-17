package org.example.toy_restboard.domain.dto.boarddto;

import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BoardCreateDto {
    private String title;
    private String content;

}
