package org.example.toy_restboard.web.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.toy_restboard.domain.dto.boarddto.BoardCreateDto;
import org.example.toy_restboard.domain.dto.boarddto.BoardDto;
import org.example.toy_restboard.domain.dto.boarddto.BoardUpdateDto;
import org.example.toy_restboard.domain.dto.userdto.LoginUser;
import org.example.toy_restboard.domain.entity.User;
import org.example.toy_restboard.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<String> createBoard(@RequestBody BoardCreateDto boardCreateDto, @AuthenticationPrincipal LoginUser loginUser) {
        log.info("boardDTo{}",boardCreateDto.getContent());
        log.info("context {}",loginUser.getUser());
//        boardService.createBoard(boardCreateDto, context);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시글이 정상적으로 등록됐습니다.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable("id") Long id, @RequestBody BoardUpdateDto boardUpdateDto, @AuthenticationPrincipal LoginUser accountContext) {
        try {
//            boardService.updateBoard(id, boardUpdateDto, accountContext);
            return ResponseEntity.ok("보드가 정상적으로 수정됐습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<BoardDto>> findAllBoards() {
        List<BoardDto> boards = boardService.findAllBoards();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> findBoardById(@PathVariable("id") Long id) {
        BoardDto boardDto = boardService.findBoardById(id);
        return ResponseEntity.ok(boardDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("id") Long id, @AuthenticationPrincipal LoginUser accountContext) {
        try {
//            boardService.deleteBoard(id, accountContext);
            return ResponseEntity.ok("게시글이 정상적으로 삭제됐습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
