package org.example.toy_restboard.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.toy_restboard.domain.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Slf4j
public class CustomResponseUtil {

    public static void fail(HttpServletResponse response, String msg, HttpStatus status) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDto<?> responseDto = new ResponseDto<>(-1, msg, null);
            String responseBody = objectMapper.writeValueAsString(responseDto);
            response.setStatus(status.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(responseBody);
        } catch (Exception e) {
            log.error("서버 파싱 에러 {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void success(HttpServletResponse response, String msg, Object dto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDto<?> responseDto = new ResponseDto<>(1, "로그인 성공", dto);
            String responseBody = objectMapper.writeValueAsString(responseDto);
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(responseBody);
        } catch (Exception e) {
            log.error("서버 파싱 에러 {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
