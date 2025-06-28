package com.sbs.java.board.boudedContext.member.service;

import com.sbs.java.board.boudedContext.global.containerr.Container;
import com.sbs.java.board.boudedContext.member.repository.MemberRepository;

public class MemberService {
  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = Container.memberRepository;
  }

  public void join(String username, String password, String name) {
    memberRepository.join(username, password, name);
  }
}
