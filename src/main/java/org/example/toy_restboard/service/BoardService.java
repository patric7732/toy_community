package org.example.toy_restboard.service;

import lombok.RequiredArgsConstructor;
import org.example.toy_restboard.domain.dto.boarddto.BoardCreateDto;
import org.example.toy_restboard.domain.dto.boarddto.BoardDto;
import org.example.toy_restboard.domain.dto.boarddto.BoardUpdateDto;
import org.example.toy_restboard.domain.dto.userdto.AccountContext;
import org.example.toy_restboard.domain.entity.Board;
import org.example.toy_restboard.domain.entity.User;
import org.example.toy_restboard.repository.BoardRepository;
import org.example.toy_restboard.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    @Transactional
    public void createBoard(BoardCreateDto boardCreateDto, AccountContext accountContext) {
        User user = userRepository.findByLoginId(accountContext.getUsername()).orElseThrow();

        Board board = new Board();
        board.setUser(user);
        board.setTitle(boardCreateDto.getTitle());
        board.setContent(boardCreateDto.getContent());
        boardRepository.save(board);
    }

    @Transactional
    public void updateBoard(Long boardId, BoardUpdateDto boardUpdateDto, AccountContext accountContext) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Board board = optionalBoard.orElse(null);
        if (!board.getUser().getLoginId().equals(accountContext.getUsername())) {
            throw new RuntimeException("게시글을 수정할 권한이 없습니다.");
        }
        board.setTitle(boardUpdateDto.getTitle());
        board.setContent(boardUpdateDto.getContent());
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public List<BoardDto> findAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardDto findBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        return mapToDto(board);
    }

    @Transactional
    public void deleteBoard(Long boardId, @AuthenticationPrincipal AccountContext accountContext) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Board board = optionalBoard.get();
        if (!board.getUser().getLoginId().equals(accountContext.getUsername())) {
            throw new RuntimeException("게시글을 삭제할 권한이 없습니다.");
        }
        boardRepository.delete(board);

    }

    private BoardDto mapToDto(Board board) {
        return new BoardDto(
                board.getBoardId(),
                board.getUser().getLoginId(),
                board.getTitle(),
                board.getContent(),
                board.getCreatedAt(),
                board.getUpdatedAt()
        );
    }
}