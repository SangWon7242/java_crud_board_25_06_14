package com.sbs.java.board.boudedContext.global.containerr;

import com.sbs.java.board.boudedContext.article.controller.ArticleController;
import com.sbs.java.board.boudedContext.article.repository.ArticleRepository;
import com.sbs.java.board.boudedContext.article.service.ArticleService;
import com.sbs.java.board.boudedContext.board.repository.BoardRepository;
import com.sbs.java.board.boudedContext.board.service.BoardService;
import com.sbs.java.board.boudedContext.global.interceptor.NeedLoginInterceptor;
import com.sbs.java.board.boudedContext.global.interceptor.NeedLogoutInterceptor;
import com.sbs.java.board.boudedContext.global.session.Session;
import com.sbs.java.board.boudedContext.member.controller.MemberController;
import com.sbs.java.board.boudedContext.member.repository.MemberRepository;
import com.sbs.java.board.boudedContext.member.service.MemberService;

import java.util.Scanner;

// Container 클래스는 전역적으로 사용되는 객체들을 담는 컨테이너 역할을 합니다.
public class Container {
  public static Scanner sc;
  public static Session session;

  public static NeedLoginInterceptor needLoginInterceptor;
  public static NeedLogoutInterceptor needLogoutInterceptor;

  public static BoardRepository boardRepository;
  public static MemberRepository memberRepository;
  public static ArticleRepository articleRepository;

  public static BoardService boardService;
  public static MemberService memberService;
  public static ArticleService articleService;

  public static MemberController memberController;
  public static ArticleController articleController;

  // static은 프로그램 시작 시 한 번만 실행된다.
  static {
    sc = new Scanner(System.in);
    session = new Session();

    needLoginInterceptor = new NeedLoginInterceptor();
    needLogoutInterceptor = new NeedLogoutInterceptor();

    boardRepository = new BoardRepository();
    memberRepository = new MemberRepository();
    articleRepository = new ArticleRepository();

    boardService = new BoardService();
    memberService = new MemberService();
    articleService = new ArticleService();

    memberController = new MemberController();
    articleController = new ArticleController();
  }
}
