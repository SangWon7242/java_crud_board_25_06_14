package com.sbs.java.board;

import com.sbs.java.board.boudedContext.article.Article;
import com.sbs.java.board.boudedContext.global.base.Rq;
import com.sbs.java.board.boudedContext.global.containerr.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Main {
  static List<Article> articles = new ArrayList<>();
  static int articleLastId = 0;

  static void makeArticleTestData() {
    IntStream.rangeClosed(1, 100)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public static void main(String[] args) {
    makeArticleTestData();

    System.out.println("== 자바 CRUD 게시판 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = Container.sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.urlPath().equals("/usr/article/write")) {
        actionArticleDoWrite(rq);
      } else if (rq.urlPath().equals("/usr/article/detail")) {
        actionArticleShowDetail(rq);
      } else if (rq.urlPath().equals("/usr/article/list")) {
        actionArticleShowList(rq);
      } else if (rq.urlPath().equals("/usr/article/modify")) {
        actionArticleDoModify(rq);
      } else if (rq.urlPath().equals("/usr/article/delete")) {
        actionArticleDoDelete(rq);
      } else if (rq.urlPath().equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      } else {
        System.out.println("잘못 입력된 명령어입니다.");
      }
    }

    System.out.println("== 자바 CRUD 게시판 종료 ==");

    Container.sc.close();
  }

  static void actionArticleDoDelete(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (!params.containsKey("id")) {
      System.out.println("id 값을 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id 값을 정수 형태로 입력해주세요.");
      return;
    }

    Article article = findById(articles, id);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    articles.remove(article);
    System.out.printf("%d번 게시물이 삭제되었습니다.\n", article.id);
  }

  static void actionArticleDoModify(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (!params.containsKey("id")) {
      System.out.println("id 값을 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id 값을 정수 형태로 입력해주세요.");
      return;
    }

    Article article = findById(articles, id);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.println("== 게시물 수정 ==");
    System.out.print("제목 : ");
    article.subject = Container.sc.nextLine();

    System.out.print("내용 : ");
    article.content = Container.sc.nextLine();
    ;

    System.out.printf("%d번 게시물이 수정되었습니다.\n", article.id);
  }

  private static void actionArticleDoWrite(Rq rq) {
    articleLastId = articles.get(articles.size() - 1).id;

    System.out.println("== 게시물 작성 ==");
    System.out.print("제목 : ");
    String subject = Container.sc.nextLine();

    if (subject.trim().isEmpty()) {
      System.out.println("제목을 입력해주세요.");
      return;
    }

    System.out.print("내용 : ");
    String content = Container.sc.nextLine();

    if (content.trim().isEmpty()) {
      System.out.println("내용을 입력해주세요.");
      return;
    }

    int id = ++articleLastId;

    Article article = new Article(id, subject, content);
    articles.add(article);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
  }

  static void actionArticleShowDetail(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (!params.containsKey("id")) {
      System.out.println("id 값을 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id 값을 정수 형태로 입력해주세요.");
      return;
    }

    Article article = findById(articles, id);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.printf("== %d번 게시물 상세보기 ==\n", article.id);
    System.out.printf("번호 : %d\n", article.id);
    System.out.printf("제목 : %s\n", article.subject);
    System.out.printf("내용 : %s\n", article.content);

  }

  static void actionArticleShowList(Rq rq) {
    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Map<String, String> params = rq.getParams();

    // 검색 시작
    List<Article> filteredArticles = articles;

    if (params.containsKey("searchKeyword")) {
      String searchKeyword = params.get("searchKeyword");

      // v1 : 스트림 사용 안한 방식
          /*
          filteredArticles = new ArrayList<>();

          for(Article article : articles) {
            if(article.subject.contains(searchKeyword) || article.content.contains(searchKeyword)) {
              filteredArticles.add(article);
            }
          }
          */

      // v2 : 스트림 사용 한 방식
      filteredArticles = articles.stream()
          .filter(article -> article.subject.contains(searchKeyword) || article.content.contains(searchKeyword))
          .toList(); // collect(Collectors.toList()) 대신 toList() 사용

      if (filteredArticles.isEmpty()) {
        System.out.printf("검색어 '%s'에 해당하는 게시물이 없습니다.\n", searchKeyword);
        return;
      }
    }
    // 검색 끝

    // new ArrayList<>(articles) : 원본 기반으로 복사본 생성
    List<Article> sortedArticles = new ArrayList<>(filteredArticles);

    if (params.containsKey("orderBy")) {
      String orderBy = params.get("orderBy");

      switch (orderBy) {
        case "idAsc":
          // 오름차순 : 작은 수가 앞으로
          sortedArticles.sort((a, b) -> a.id - b.id);
          break;
        case "idDesc":
        default:
          sortedArticles.sort((a, b) -> b.id - a.id); // 내림차순 : 큰 수가 앞으로
          break;
      }
    } else {
      sortedArticles.sort((a, b) -> b.id - a.id);
    }

    System.out.printf("== 게시물 리스트(총 %d개) ==\n", sortedArticles.size());
    System.out.println("번호 | 제목");

    sortedArticles.forEach(
        article -> System.out.printf("%d | %s\n", article.id, article.subject)
    );
  }

  static Article findById(List<Article> articles, int id) {
    return articles.stream()
        .filter(article -> article.id == id)
        .findFirst() // 첫 번째 요소를 찾음
        .orElse(null); // 찾지 못한 경우 null 반환
  }
}