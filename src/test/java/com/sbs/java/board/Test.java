package com.sbs.java.board;

import java.util.*;

public class Test {
  public static void main(String[] args) {
    String queryString1 = "page=1&searchKeyword=제목1&searchKeywordTypeCode=subject&boardId=1";

    // 압축 해제 시작
    String[] queryStringBits1 = queryString1.split("&");

    Map<String, String> params1 = new LinkedHashMap<>();

    for (String bit : queryStringBits1) {
      String[] bitBits = bit.split("=");
      String paramName = bitBits[0];
      String paramValue = bitBits[1];

      params1.put(paramName, paramValue);
    }
    // 압축 해제 끝

    System.out.println(params1);

    System.out.println("== 반복문을 이용한 데이터 순회 ==");
    params1.forEach((key, value) -> System.out.printf("%s : %s\n", key, value));

    String queryString2 = "page=2&searchKeyword=내용1&searchKeywordTypeCode=content&boardId=2";

    // 압축 해제 시작
    String[] queryStringBits2 = queryString2.split("&");

    Map<String, String> params2 = new LinkedHashMap<>();

    for (String bit : queryStringBits2) {
      String[] bitBits = bit.split("=");
      String paramName = bitBits[0];
      String paramValue = bitBits[1];

      params2.put(paramName, paramValue);
    }
    // 압축 해제 끝

    System.out.println(params2);

    System.out.println("== 반복문을 이용한 데이터 순회 ==");
    params2.forEach((key, value) -> System.out.printf("%s : %s\n", key, value));
  }
}
