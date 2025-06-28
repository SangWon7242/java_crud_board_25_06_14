package com.sbs.java.board.boudedContext.member.controller;

import com.sbs.java.board.boudedContext.global.base.Rq;
import com.sbs.java.board.boudedContext.global.containerr.Container;
import com.sbs.java.board.boudedContext.member.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberController {
  public List<Member> members;

  public MemberController() {
    members = new ArrayList<>();
  }

  public void doJoin(Rq rq) {
    String username;
    String password;
    String passwordConfirm;
    String name;

    System.out.println("== 회원 가입 ==");

    // 로그인 아이디 입력
    while (true) {
      System.out.print("로그인 아이디 : ");
      username = Container.sc.nextLine();

      if(username.trim().isEmpty()) {
        System.out.println("로그인 아이디를 입력해주세요.");
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

    Member member = new Member(username, password, name);
    members.add(member);

    System.out.printf("'%s'님 회원 가입이 완료되었습니다.\n", member.getUsername());
  }
}
