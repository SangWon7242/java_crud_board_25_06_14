package com.sbs.java.board.boudedContext.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Member {
  private static int lastId;
  private int id;
  private String username;
  private String password;
  private String name;

  static {
    lastId = 0;
  }

  public Member(String username, String password, String name) {
    this(++lastId, username, password, name);
  }
}
