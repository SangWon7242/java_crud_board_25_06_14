package com.sbs.java.board.boudedContext.article;

public class Article {
  public int id;
  public String subject;
  public String content;

  // 객체가 만들어질 때 한번 실행!
  public  Article(int id, String subject, String content) {
    this.id = id;
    this.subject = subject;
    this.content = content;
  }

  @Override
  public String toString() {
    return "{id : %d, subject: \"%s\", content: \"%s\"}".formatted(id, subject, content);
  }
}