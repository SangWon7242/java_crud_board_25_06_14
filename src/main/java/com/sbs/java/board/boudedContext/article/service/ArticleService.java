package com.sbs.java.board.boudedContext.article.service;

import com.sbs.java.board.boudedContext.article.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArticleService {
  public List<Article> articles;
  public int articleLastId;

  public ArticleService() {
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
    // 검색 시작
    List<Article> filteredArticles = new ArrayList<>(articles);

    if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
      filteredArticles = articles.stream()
          .filter(article -> article.subject.contains(searchKeyword) || article.content.contains(searchKeyword))
          .collect(Collectors.toList());
    }
    // 검색 끝

    // 정렬 시작
    List<Article> sortedArticles = filteredArticles;

    if (orderBy != null && !orderBy.trim().isEmpty()) {
      switch (orderBy) {
        case "idAsc":
          // 오름차순 : 작은 수가 앞으로
          sortedArticles.sort((a, b) -> a.id - b.id);
          break;
        case "idDesc":
        default:
          sortedArticles.sort((a, b) -> b.id - a.id); // 내림차순 : 큰 수가 앞으로
          break;
      }
    } else {
      sortedArticles.sort((a, b) -> b.id - a.id);
    }

    return sortedArticles;
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
