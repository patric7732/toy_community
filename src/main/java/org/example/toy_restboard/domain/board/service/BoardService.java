package org.example.toy_restboard.domain.board.service;

import lombok.RequiredArgsConstructor;
import org.example.toy_restboard.domain.board.repository.BoardRepository;
import org.example.toy_restboard.domain.board.dto.BoardCreateDto;
import org.example.toy_restboard.domain.board.dto.BoardDto;
import org.example.toy_restboard.domain.board.dto.BoardUpdateDto;
import org.example.toy_restboard.domain.board.entity.Board;
import org.example.toy_restboard.global.config.security.LoginUser;
import org.example.toy_restboard.domain.user.entity.User;
import org.example.toy_restboard.domain.user.repository.UserRepository;
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
    public void createBoard(BoardCreateDto boardCreateDto, @AuthenticationPrincipal LoginUser loginUser) {
//        LoginUser user = userRepository.findByLoginId(user.getUsername());
        Optional<User> optionalUser = userRepository.findByLoginId(loginUser.getUser().getLoginId());
        Board board = new Board();
        board.setUser(optionalUser.get());
        board.setTitle(boardCreateDto.getTitle());
        board.setContent(boardCreateDto.getContent());
        boardRepository.save(board);
    }

    @Transactional
    public void updateBoard(Long boardId, BoardUpdateDto boardUpdateDto, @AuthenticationPrincipal LoginUser loginUser) {
        Optional<User> optionalUser = userRepository.findByLoginId(loginUser.getUser().getLoginId());
        User user = optionalUser.get();
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Board board = optionalBoard.orElse(null);
        if (!board.getUser().getLoginId().equals(user.getLoginId())) {
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
    public void deleteBoard(Long boardId, @AuthenticationPrincipal LoginUser loginUser) {
        Optional<User> optionalUser = userRepository.findByLoginId(loginUser.getUser().getLoginId());
        User user = optionalUser.get();
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Board board = optionalBoard.get();
        if (!board.getUser().getLoginId().equals(user.getLoginId())) {
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
