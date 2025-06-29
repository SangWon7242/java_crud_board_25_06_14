package com.sbs.java.board.boudedContext.article.controller;

import com.sbs.java.board.boudedContext.article.Article;
import com.sbs.java.board.boudedContext.article.service.ArticleService;
import com.sbs.java.board.boudedContext.global.base.Rq;
import com.sbs.java.board.boudedContext.global.containerr.Container;
import com.sbs.java.board.boudedContext.global.controller.Controller;
import com.sbs.java.board.boudedContext.member.dto.Member;

import java.util.List;

public class ArticleController implements Controller {
  private ArticleService articleService;

  public ArticleController() {
    articleService = new ArticleService();
  }

  @Override
  public void doAction(Rq rq) {
    if(rq.getActionPath().equals("/usr/article/write")) {
      doWrite(rq);
    } else if (rq.getActionPath().equals("/usr/article/detail")) {
      showDetail(rq);
    } else if (rq.getActionPath().equals("/usr/article/list")) {
      showList(rq);
    } else if (rq.getActionPath().equals("/usr/article/modify")) {
      doModify(rq);
    } else if (rq.getActionPath().equals("/usr/article/delete")) {
      doDelete(rq);
    }
  }

  public void doWrite(Rq rq) {
    System.out.println("== 게시물 작성 ==");
    System.out.print("제목 : ");
    String subject = Container.sc.nextLine();

    if (subject.trim().isEmpty()) {
      System.out.println("제목을 입력해주세요.");
      return;
    }

    System.out.print("내용 : ");
    String content = Container.sc.nextLine();

    if (content.trim().isEmpty()) {
      System.out.println("내용을 입력해주세요.");
      return;
    }

    Member member = rq.getLoginedMember();
    int memberId = member.getId();

    int id = articleService.write(subject, content, memberId);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id 값을 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.printf("== %d번 게시물 상세보기 ==\n", article.getId());
    System.out.printf("번호 : %d\n", article.getId());
    System.out.printf("제목 : %s\n", article.getSubject());
    System.out.printf("내용 : %s\n", article.getContent());
    System.out.printf("작성자 : %s\n", article.getWriterName());
  }

  public void showList(Rq rq) {
    String searchKeyword = rq.getParam("searchKeyword", "");
    String orderBy = rq.getParam("orderBy", "idDesc");

    List<Article> articles = articleService.findAll(searchKeyword, orderBy);

    System.out.printf("== 게시물 리스트(총 %d개) ==\n", articles.size());
    System.out.println("번호 | 제목 | 작성자");

    articles.forEach(
        article -> System.out.printf("%d | %s | %s\n", article.getId(), article.getSubject(), article.getWriterName())
    );
  }

  public void doModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id 값을 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Member member = rq.getLoginedMember();

    if(article.getMemberId() != member.getId()) {
      System.out.println("수정에 대한 권한이 없습니다.");
      return;
    }

    System.out.println("== 게시물 수정 ==");
    System.out.print("제목 : ");
    String subject = Container.sc.nextLine();

    System.out.print("내용 : ");
    String content = Container.sc.nextLine();

    articleService.modify(id, subject, content);
    System.out.printf("%d번 게시물이 수정되었습니다.\n", article.getId());
  }

  public void doDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id 값을 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Member member = rq.getLoginedMember();

    if(article.getMemberId() != member.getId()) {
      System.out.println("삭제에 대한 권한이 없습니다.");
      return;
    }

    articleService.delete(id);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", article.getId());
  }
}
