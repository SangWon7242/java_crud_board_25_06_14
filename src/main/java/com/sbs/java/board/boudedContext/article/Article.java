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
  private String regDate;
  private String updateDate;
  private String subject;
  private String content;
  private int memberId;
  private String writerName;

  static {
    lastId = 0;
  }

  public Article(String regDate, String updateDate, String subject, String content, int memberId, String writerName) {
    this(++lastId, regDate, updateDate, subject, content, memberId, writerName);
  }
}