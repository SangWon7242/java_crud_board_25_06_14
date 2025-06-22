package com.sbs.java.board.boudedContext.global.containerr;

import com.sbs.java.board.boudedContext.article.controller.ArticleController;

import java.util.Scanner;

// Container 클래스는 전역적으로 사용되는 객체들을 담는 컨테이너 역할을 합니다.
public class Container {
  public static Scanner sc;

  public static ArticleController articleController;

  // static은 프로그램 시작 시 한 번만 실행된다.
  static {
    sc = new Scanner(System.in);

    articleController = new ArticleController();
  }
}
