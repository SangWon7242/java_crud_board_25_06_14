package com.sbs.java.board.boudedContext.board.service;

import com.sbs.java.board.boudedContext.board.dto.Board;
import com.sbs.java.board.boudedContext.board.repository.BoardRepository;
import com.sbs.java.board.boudedContext.global.containerr.Container;

public class BoardService {
  private BoardRepository boardRepository;

  public BoardService() {
    boardRepository = Container.boardRepository;
  }

  public Board findById(int id) {
    return boardRepository.findById(id);
  }
}
