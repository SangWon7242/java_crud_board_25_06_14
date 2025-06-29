package com.sbs.java.board.boudedContext.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Member {
  private static int lastId;
  private int id;
  private String regDate;
  private String updateDate;
  private String username;
  private String password;
  private String name;

  public String getType() {
    return isAdmin() ? "관리자" : "일반회원";
  }

  public boolean isAdmin() {
    return username.equals("admin");
  }

  static {
    lastId = 0;
  }

  public Member(String regDate, String updateDate, String username, String password, String name) {
    this(++lastId, regDate, updateDate, username, password, name);
  }
}
