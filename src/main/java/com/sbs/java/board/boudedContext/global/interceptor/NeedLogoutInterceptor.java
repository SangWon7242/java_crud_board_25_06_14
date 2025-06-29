package com.sbs.java.board.boudedContext.global.interceptor;

import com.sbs.java.board.boudedContext.global.base.Rq;

public class NeedLogoutInterceptor implements Interceptor {
  @Override
  public boolean run(Rq rq) {
    if(rq.isLogout()) return true;

    return switch (rq.getActionPath()) {
      case "/usr/member/join",
           "/usr/member/login" -> {
        System.out.println("로그아웃 후 이용해주세요.");
        yield false;
      }

      default -> true;
    };
  }
}
