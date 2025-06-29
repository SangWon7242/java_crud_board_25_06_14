package com.sbs.java.board;

import com.sbs.java.board.boudedContext.global.base.Rq;
import com.sbs.java.board.boudedContext.global.containerr.Container;
import com.sbs.java.board.boudedContext.global.controller.Controller;
import com.sbs.java.board.boudedContext.global.interceptor.Interceptor;
import com.sbs.java.board.boudedContext.member.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class App {
  public void run() {

    System.out.println("== 자바 CRUD 게시판 ==");

    while (true) {
      Rq rq = new Rq();

      Member member = rq.getLoginedMember();
      String promptName = "명령";

      if(member != null) {
        promptName = member.getUsername();
      }

      System.out.printf("%s) ", promptName);

      String cmd = Container.sc.nextLine();

      rq.setCommand(cmd);
      rq.getActionPath();

      if(!runInterceptor(rq)) continue;

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

  private boolean runInterceptor(Rq rq) {
    List<Interceptor> interceptors = new ArrayList<>();

    interceptors.add(Container.needLoginInterceptor);
    interceptors.add(Container.needLogoutInterceptor);

    for( Interceptor interceptor : interceptors) {
      if (!interceptor.run(rq)) {
        return false;
      }
    }

    return true;
  }
}
