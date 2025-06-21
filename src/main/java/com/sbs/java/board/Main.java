package com.sbs.java.board;

import com.sbs.java.board.boudedContext.article.Article;
import com.sbs.java.board.boudedContext.global.base.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
  static void makeArticleTestData(List<Article> articles) {
    IntStream.rangeClosed(1, 3)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    List<Article> articles = new ArrayList<>();
    makeArticleTestData(articles);

    int articleLastId = articles.get(articles.size() - 1).id;
    System.out.println("== 자바 CRUD 게시판 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.urlPath().equals("/usr/article/write")) {
        System.out.println("== 게시물 작성 ==");
        System.out.print("제목 : ");
        String subject = sc.nextLine();

        if (subject.trim().isEmpty()) {
          System.out.println("제목을 입력해주세요.");
          continue;
        }

        System.out.print("내용 : ");
        String content = sc.nextLine();

        if (content.trim().isEmpty()) {
          System.out.println("내용을 입력해주세요.");
          continue;
        }

        int id = ++articleLastId;

        Article article = new Article(id, subject, content);
        articles.add(article);

        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
      } else if (rq.urlPath().equals("/usr/article/detail")) {
        Map<String, String> params = rq.getParams();

        if(!params.containsKey("id")) {
          System.out.println("id 값을 입력해주세요.");
          continue;
        }

        int id = 0;

        try {
          id = Integer.parseInt(params.get("id"));
        } catch (NumberFormatException e) {
          System.out.println("id 값을 정수 형태로 입력해주세요.");
          continue;
        }

        System.out.println("== 게시물 상세보기 ==");

        if(id > articles.size()) {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
          continue;
        }

        Article article = articles.get(id - 1);

        if (article == null) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.printf("== %d번 게시물 조회 ==\n", article.id);
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.subject);
        System.out.printf("내용 : %s\n", article.content);


      } else if (rq.urlPath().equals("/usr/article/list")) {
        if(articles.isEmpty()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        Map<String, String> params = rq.getParams();

        boolean orderByIdDesc = true;

        if(params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
          orderByIdDesc = false;
        }

        System.out.println("== 게시물 리스트 ==");
        System.out.println("번호 | 제목");

        if(orderByIdDesc) {
          for(int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.printf("%d | %s\n", article.id, article.subject);
          }
        }
        else {
          /*
          for(Article article : articles) {
            System.out.printf("%d | %s\n", article.id, article.subject);
          }
          */

          articles.forEach(
              article -> System.out.printf("%d | %s\n", article.id, article.subject)
          );
        }

      } else if (rq.urlPath().equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      } else {
        System.out.println("잘못 입력된 명령어입니다.");
      }
    }

    System.out.println("== 자바 CRUD 게시판 종료 ==");

    sc.close();
  }
}