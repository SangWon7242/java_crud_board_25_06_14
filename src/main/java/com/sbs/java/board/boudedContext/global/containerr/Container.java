package com.sbs.java.board.boudedContext.global.containerr;

import com.sbs.java.board.boudedContext.article.controller.ArticleController;
import com.sbs.java.board.boudedContext.article.repository.ArticleRepository;
import com.sbs.java.board.boudedContext.article.service.ArticleService;
import com.sbs.java.board.boudedContext.global.session.Session;
import com.sbs.java.board.boudedContext.member.controller.MemberController;
import com.sbs.java.board.boudedContext.member.repository.MemberRepository;
import com.sbs.java.board.boudedContext.member.service.MemberService;

import java.util.Scanner;

// Container 클래스는 전역적으로 사용되는 객체들을 담는 컨테이너 역할을 합니다.
public class Container {
  public static Scanner sc;
  public static Session session;

  public static MemberRepository memberRepository;
  public static ArticleRepository articleRepository;

  public static MemberService memberService;
  public static ArticleService articleService;

  public static MemberController memberController;
  public static ArticleController articleController;

  // static은 프로그램 시작 시 한 번만 실행된다.
  static {
    sc = new Scanner(System.in);
    session = new Session();


    memberRepository = new MemberRepository();
    articleRepository = new ArticleRepository();

    memberService = new MemberService();
    articleService = new ArticleService();

    memberController = new MemberController();
    articleController = new ArticleController();
  }
}
