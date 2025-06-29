package com.sbs.java.board.boudedContext.member.controller;

import com.sbs.java.board.boudedContext.global.base.Rq;
import com.sbs.java.board.boudedContext.global.containerr.Container;
import com.sbs.java.board.boudedContext.global.controller.Controller;
import com.sbs.java.board.boudedContext.member.dto.Member;
import com.sbs.java.board.boudedContext.member.service.MemberService;

public class MemberController implements Controller {
  private MemberService memberService;

  public MemberController() {
    memberService = Container.memberService;
  }

  @Override
  public void doAction(Rq rq) {
    if(rq.getActionPath().equals("/usr/member/join")) {
      doJoin(rq);
    } else if (rq.getActionPath().equals("/usr/member/login")) {
      doLogin(rq);
    } else if (rq.getActionPath().equals("/usr/member/logout")) {
      doLogout(rq);
    } else if (rq.getActionPath().equals("/usr/member/myPage")) {
      showMyPage(rq);
    }
  }

  private void showMyPage(Rq rq) {
    Member member = rq.getLoginedMember();
    System.out.printf("== 마이페이지 (%s) ==\n", member.getUsername());
    System.out.printf("아이디 : %s\n", member.getUsername());
    System.out.printf("가입날짜 : %s\n", member.getRegDate());
    System.out.printf("이름 : %s\n", member.getName());
  }

  private void doLogout(Rq rq) {
    rq.logout();
    System.out.println("로그아웃 되었습니다.");
  }

  public void doJoin(Rq rq) {
    String username;
    String password;
    String passwordConfirm;
    String name;
    Member member;

    System.out.println("== 회원 가입 ==");

    // 로그인 아이디 입력
    while (true) {
      System.out.print("로그인 아이디 : ");
      username = Container.sc.nextLine();

      if(username.trim().isEmpty()) {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      member = memberService.findByUsername(username);

      if(member != null) {
        System.out.printf("'%s'(은)는 이미 사용 중인 아이디입니다. 다른 아이디를 입력해주세요.\n", username);
        continue;
      }

      break;
    }

    // 로그인 비밀번호 입력
    while (true) {
      System.out.print("로그인 비밀번호 : ");
      password = Container.sc.nextLine();

      if(password.trim().isEmpty()) {
        System.out.println("로그인 비밀번호를 입력해주세요.");
        continue;
      }

      while (true) {
        System.out.print("로그인 비밀번호 확인 : ");
        passwordConfirm = Container.sc.nextLine();

        if(passwordConfirm.trim().isEmpty()) {
          System.out.println("로그인 비밀번호 확인을 입력해주세요.");
          continue;
        }

        if(!passwordConfirm.equals(password)) {
          System.out.println("로그인 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
          continue;
        }

        break;
      }
      
      break;
    }

    // 이름 입력
    while (true) {
      System.out.print("이름 : ");
      name = Container.sc.nextLine();

      if(name.trim().isEmpty()) {
        System.out.println("이름 입력해주세요.");
        continue;
      }

      break;
    }

    memberService.join(username, password, name);

    System.out.printf("'%s'님 회원 가입이 완료되었습니다.\n", username);
  }

  public void doLogin(Rq rq) {
    if(rq.isLogined()) {
      System.out.println("이미 로그인되어 있습니다.");
      return;
    }

    String username;
    String password;
    Member member;

    System.out.println("== 로그인 ==");

    // 로그인 아이디 입력
    while (true) {
      System.out.print("로그인 아이디 : ");
      username = Container.sc.nextLine();

      if(username.trim().isEmpty()) {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      member = memberService.findByUsername(username);

      if(member == null) {
        System.out.printf("'%s'(은)는 존재하지 않는 아이디입니다.\n", username);
        continue;
      }

      break;
    }

    // 로그인 비밀번호 입력
    while (true) {
      System.out.print("로그인 비밀번호 : ");
      password = Container.sc.nextLine();

      if(password.trim().isEmpty()) {
        System.out.println("로그인 비밀번호를 입력해주세요.");
        continue;
      }

      if(!member.getPassword().equals(password)) {
        System.out.println("로그인 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
        continue;
      }

      break;
    }

    rq.login(member);

    System.out.println("로그인 되었습니다.");
  }
}
