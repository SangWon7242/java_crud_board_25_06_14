package com.sbs.java.board.boudedContext.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {
  private static int lastId;
  private int id;
  private String regDate;
  private String updateDate;
  private String name;
  private String code;

  static {
    lastId = 0;
  }

  public Board(String regDate, String updateDate, String name, String code) {
    this(++lastId, regDate, updateDate, name, code);
  }
}
