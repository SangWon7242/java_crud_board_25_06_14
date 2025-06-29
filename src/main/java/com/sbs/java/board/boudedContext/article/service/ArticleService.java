package com.sbs.java.board.boudedContext.article.service;

import com.sbs.java.board.boudedContext.article.Article;
import com.sbs.java.board.boudedContext.article.repository.ArticleRepository;
import com.sbs.java.board.boudedContext.global.containerr.Container;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.articleRepository;
  }

  public int write(String subject, String content, int memberId) {
    String writerName = Container.memberService.findById(memberId).getName();

    return articleRepository.write(subject, content, memberId, writerName);
  }

  public List<Article> findAll(String searchKeyword, String orderBy) {
    return articleRepository.findAll(searchKeyword, orderBy);
  }

  public void modify(int id, String subject, String content) {
    articleRepository.modify(id, subject, content);
  }

  public void delete(int id) {
    articleRepository.delete(id);
  }

  public Article findById(int id) {
    return articleRepository.findById(id);
  }
}
