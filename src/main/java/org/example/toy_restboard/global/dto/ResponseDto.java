package org.example.toy_restboard.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private final Integer code; // 성공 1, 실패 -1
    private final String msg;
    private final T data;

}
