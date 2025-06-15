package com.sbs.java.board;

import java.util.*;

public class Test {
  public static void main(String[] args) {
    String queryString = "page=1&searchKeyword=제목1&searchKeywordTypeCode=subject&boardId=1";
    String[] queryStringBits = queryString.split("&");

    // Map<String, String> params = new HashMap<>();
    Map<String, String> params = new LinkedHashMap<>();

    for (String bit : queryStringBits) {
      String[] bitBits = bit.split("=");
      String paramName = bitBits[0];
      String paramValue = bitBits[1];

      params.put(paramName, paramValue);
    }

    System.out.println(params);

    System.out.println("== 원하는 것을 하나씩 뽑아오기 ==");
    System.out.printf("page : %d\n", Integer.parseInt(params.get("page")));
    System.out.printf("searchKeyword : %s\n", params.get("searchKeyword"));
    System.out.printf("searchKeywordTypeCode : %s\n", params.get("searchKeywordTypeCode"));
    System.out.printf("boardId : %d\n", Integer.parseInt(params.get("boardId")));

    System.out.println("== 반복문을 이용한 데이터 순회 ==");
    params.forEach((key, value) -> System.out.printf("%s : %s\n", key, value));
  }
}
