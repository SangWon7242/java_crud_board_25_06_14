package com.sbs.java.board.boudedContext.board.repository;

import com.sbs.java.board.boudedContext.board.dto.Board;
import com.sbs.java.board.boudedContext.global.standard.Ut;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
  List<Board> boards;

  public BoardRepository() {
    boards = new ArrayList<>();

    // 초기 데이터 추가 (테스트용)
    make("공지사항", "notice");
    make("자유게시판", "free");
    make("Q&A", "qna");
  }

  private void make(String name, String code) {
    String regDate = Ut.getNowDateStr();
    String updateDate = Ut.getNowDateStr();

    Board board = new Board(regDate, updateDate, name, code);
    boards.add(board);
  }

  public Board findById(int id) {
    return boards.stream()
        .filter(board -> board.getId() == id)
        .findFirst()
        .orElse(null);
  }
}
