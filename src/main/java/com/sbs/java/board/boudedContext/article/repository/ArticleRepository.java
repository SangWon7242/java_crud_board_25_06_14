package com.sbs.java.board.boudedContext.article.repository;

import com.sbs.java.board.boudedContext.article.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArticleRepository {
  public List<Article> articles;
  public int articleLastId;


  public ArticleRepository() {
    articles = new ArrayList<>();

    makeTestData();

    articleLastId = articles.get(articles.size() - 1).id;
  }

  void makeTestData() {
    IntStream.rangeClosed(1, 100)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public int write(String subject, String content) {
    int id = ++articleLastId;

    Article article = new Article(id, subject, content);
    articles.add(article);

    return id;
  }

  public List<Article> findAll(String searchKeyword, String orderBy) {
    List<Article> result = new ArrayList<>(articles);

    // 검색 적용
    result = applySearch(result, searchKeyword);

    // 정렬 적용
    result = applySorting(result, orderBy);

    return result;
  }

  // 정렬 기능을 수행하는 메서드
  private List<Article> applySorting(List<Article> articles, String orderBy) {
    if (orderBy != null && !orderBy.trim().isEmpty()) {
      switch (orderBy) {
        case "idAsc":
          // 오름차순 : 작은 수가 앞으로
          articles.sort((a, b) -> a.id - b.id);
          break;
        case "idDesc":
        default:
          articles.sort((a, b) -> b.id - a.id); // 내림차순 : 큰 수가 앞으로
          break;
      }
    }

    return articles;
  }

  // 검색 기능을 수행하는 메서드
  private List<Article> applySearch(List<Article> articles, String searchKeyword) {
    if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
      return articles;
    }

    return articles.stream()
        .filter(article -> article.subject.contains(searchKeyword) || article.content.contains(searchKeyword))
        .collect(Collectors.toList());
  }

  public void modify(int id, String subject, String content) {
    Article article = findById(id);

    if (article == null) return;

    article.subject = subject;
    article.content = content;
  }

  public void delete(int id) {
    Article article = findById(id);

    if (article == null) return;

    articles.remove(article);
  }

  public Article findById(int id) {
    return articles.stream()
        .filter(article -> article.id == id)
        .findFirst() // 첫 번째 요소를 찾음
        .orElse(null); // 찾지 못한 경우 null 반환
  }
}
