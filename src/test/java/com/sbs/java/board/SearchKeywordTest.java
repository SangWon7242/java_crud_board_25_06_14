package com.sbs.java.board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SearchKeywordTest {
  public static void main(String[] args) {
    List<Article> articles = new ArrayList<>();

    IntStream.rangeClosed(1, 5)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));

    articles.add(new Article(6, "자바는 무슨 언어인가요?", "자바가 절차지향인지 객체지향인지 궁금합니다."));
    articles.add(new Article(7, "코딩 실력이 빨리 늘려면?", "코딩 실력을 빨리 늘리는 방법이 있을까요?"));
    articles.add(new Article(8, "백엔드 개발을 하기 위해서...", "백엔드 개발을 위해 어떤 언어를 배워야 하나요?"));

    articles.forEach(System.out::println);

    String searchKeyword = "백엔드";

    // v1 : 스트림 사용 안한 방식
    /*
    List<Article> filteredArticles = new ArrayList<>();

    for(Article article : articles) {
      if (article.subject.contains(searchKeyword) || article.content.contains(searchKeyword)) {
        filteredArticles.add(article);
      }
    }
    */

    List<Article> filteredArticles = articles.stream()
        .filter(article -> article.subject.contains(searchKeyword) || article.content.contains(searchKeyword))
        .collect(Collectors.toList());

    System.out.println(filteredArticles);
  }
}

class Article {
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