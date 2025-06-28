package com.sbs.java.board;

import com.sbs.java.board.boudedContext.article.controller.ArticleController;
import com.sbs.java.board.boudedContext.global.base.Rq;
import com.sbs.java.board.boudedContext.global.containerr.Container;
import com.sbs.java.board.boudedContext.member.controller.MemberController;

public class App {
  public MemberController memberController;
  public ArticleController articleController;

  public App() {
    memberController = Container.memberController;
    articleController = Container.articleController;
  }

  /**
   * 자바 CRUD 게시판 프로그램의 메인 실행 메서드입니다.
   * 명령어를 입력받아 해당 작업을 수행합니다.
   */
  public void run() {

    System.out.println("== 자바 CRUD 게시판 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = Container.sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.urlPath().equals("/usr/article/write")) {
        articleController.doWrite(rq);
      } else if (rq.urlPath().equals("/usr/article/detail")) {
        articleController.showDetail(rq);
      } else if (rq.urlPath().equals("/usr/article/list")) {
        articleController.showList(rq);
      } else if (rq.urlPath().equals("/usr/article/modify")) {
        articleController.doModify(rq);
      } else if (rq.urlPath().equals("/usr/article/delete")) {
        articleController.doDelete(rq);
      } else if (rq.urlPath().equals("/usr/member/join")) {
        memberController.doJoin(rq);
      } else if (rq.urlPath().equals("/usr/member/login")) {
        memberController.doLogin(rq);
      } else if (rq.urlPath().equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      } else {
        System.out.println("잘못 입력된 명령어입니다.");
      }
    }

    System.out.println("== 자바 CRUD 게시판 종료 ==");

    Container.sc.close();
  }
}
