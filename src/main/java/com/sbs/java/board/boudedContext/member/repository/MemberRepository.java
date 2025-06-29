package com.sbs.java.board.boudedContext.member.repository;

import com.sbs.java.board.boudedContext.global.standard.Ut;
import com.sbs.java.board.boudedContext.member.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
  private List<Member> members;

  public MemberRepository() {
    members = new ArrayList<>();

    // 초기 데이터 추가 (테스트용)
    join("user1", "1234", "User One");
    join("user2", "5678", "User Two");
    join("admin", "admin", "Administrator");
  }

  public void join(String username, String password, String name) {
    String regDate = Ut.getNowDateStr();
    String updateDate = Ut.getNowDateStr();

    Member member = new Member(regDate, updateDate, username, password, name);
    members.add(member);
  }

  public Member findByUsername(String username) {
    return members.stream()
        .filter(member -> member.getUsername().equals(username))
        .findFirst()
        .orElse(null);
  }

  public Member findById(int id) {
    return members.stream()
        .filter(member -> member.getId() == id)
        .findFirst()
        .orElse(null);
  }
}
