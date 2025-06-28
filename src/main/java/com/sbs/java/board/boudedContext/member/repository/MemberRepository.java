package com.sbs.java.board.boudedContext.member.repository;

import com.sbs.java.board.boudedContext.member.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
  private List<Member> members;

  public MemberRepository() {
    members = new ArrayList<>();
  }

  public void join(String username, String password, String name) {
    Member member = new Member(username, password, name);
    members.add(member);
  }
}
