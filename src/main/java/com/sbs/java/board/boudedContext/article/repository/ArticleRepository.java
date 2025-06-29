package com.sbs.java.board.boudedContext.article.repository;

import com.sbs.java.board.boudedContext.article.Article;
import com.sbs.java.board.boudedContext.global.standard.Ut;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArticleRepository {
  private List<Article> articles;

  public ArticleRepository() {
    articles = new ArrayList<>();

    makeTestData();
  }

  void makeTestData() {
    IntStream.rangeClosed(1, 100)
        .forEach(i -> write("제목 " + i, "내용 " + i, 1, 1, "User One", "공지사항"));
  }

  public int write(String subject, String content, int memberId, int boardId, String writerName, String boardName) {
    String regDate = Ut.getNowDateStr();
    String updateDate = Ut.getNowDateStr();

    Article article = new Article(regDate, updateDate, subject, content, memberId, boardId, writerName, boardName);
    articles.add(article);

    return article.getId();
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
          articles.sort((a, b) -> a.getId() - b.getId());
          break;
        case "idDesc":
        default:
          articles.sort((a, b) -> b.getId() - a.getId()); // 내림차순 : 큰 수가 앞으로
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
        .filter(article -> article.getSubject().contains(searchKeyword) || article.getContent().contains(searchKeyword))
        .collect(Collectors.toList());
  }

  public void modify(int id, String subject, String content) {
    Article article = findById(id);

    if (article == null) return;

    // 수정 시점의 날짜를 가져옴
    String updateDate = Ut.getNowDateStr();

    article.setUpdateDate(updateDate);
    article.setSubject(subject);
    article.setContent(content);
  }

  public void delete(int id) {
    Article article = findById(id);

    if (article == null) return;

    articles.remove(article);
  }

  public Article findById(int id) {
    return articles.stream()
        .filter(article -> article.getId() == id)
        .findFirst() // 첫 번째 요소를 찾음
        .orElse(null); // 찾지 못한 경우 null 반환
  }
}
