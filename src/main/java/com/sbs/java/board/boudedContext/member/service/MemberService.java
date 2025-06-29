package com.sbs.java.board.boudedContext.member.service;

import com.sbs.java.board.boudedContext.global.containerr.Container;
import com.sbs.java.board.boudedContext.member.dto.Member;
import com.sbs.java.board.boudedContext.member.repository.MemberRepository;

import java.nio.file.Path;

public class MemberService {
  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = Container.memberRepository;
  }

  public void join(String username, String password, String name) {
    memberRepository.join(username, password, name);
  }

  public Member findByUsername(String username) {
    return memberRepository.findByUsername(username);
  }

  public Member findById(int id) {
    return memberRepository.findById(id);
  }
}
