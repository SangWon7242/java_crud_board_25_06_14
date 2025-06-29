package com.sbs.java.board.boudedContext.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
  private static int lastId;
  private int id;
  private String subject;
  private String content;
  private int memberId;

  static {
    lastId = 0;
  }

  public Article(String subject, String content, int memberId) {
    this(++lastId, subject, content, memberId);
  }
}