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
  private int boardId;
  private String writerName;
  private String boardName;

  static {
    lastId = 0;
  }

  public Article(String regDate,
                 String updateDate,
                 String subject,
                 String content,
                 int memberId,
                 int boardId,
                 String writerName,
                 String boardName) {
    this(++lastId, regDate, updateDate, subject, content, memberId, boardId, writerName, boardName);
  }
}