package com.sbs.java.board.boudedContext.article;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
  private static int lastId;
  private int id;
  private String subject;
  private String content;

  public Article(String subject, String content) {
    this(++lastId, subject, content);
  }
}