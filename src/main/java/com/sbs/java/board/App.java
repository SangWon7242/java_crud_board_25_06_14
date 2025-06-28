package com.sbs.java.board;

import com.sbs.java.board.boudedContext.global.base.Rq;
import com.sbs.java.board.boudedContext.global.containerr.Container;
import com.sbs.java.board.boudedContext.global.controller.Controller;

public class App {
  public void run() {

    System.out.println("== 자바 CRUD 게시판 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = Container.sc.nextLine();

      Rq rq = new Rq(cmd);

      rq.getActionPath();

      Controller controller = getControllerByRequestUri(rq);

      if (controller != null) {
        controller.doAction(rq);
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

  private Controller getControllerByRequestUri(Rq rq) {
    switch (rq.getControllerTypeCode()) {
      case "usr":
        switch (rq.getControllerName()) {
          case "article":
            return Container.articleController;
          case "member":
            return Container.memberController;
        }
    }

    return null;
  }
}
