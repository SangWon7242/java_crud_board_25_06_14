package com.sbs.java.board;

import java.util.*;

public class Test {
  public static void main(String[] args) {
    String queryString1 = "page=1&searchKeyword=제목1&searchKeywordTypeCode=subject&boardId=1";
    Map<String, String> params1 = Util.getParams(queryString1);
    System.out.println(params1);

    String queryString2 = "page=2&searchKeyword=내용1&searchKeywordTypeCode=content&boardId=2";
    Map<String, String> params2 = Util.getParams(queryString2);
    System.out.println(params2);
  }
}

class Util {
  static Map<String, String> getParams(String queryStr) {
    Map<String, String> params = new LinkedHashMap<>();

    String[] queryStringBits = queryStr.split("&");

    for (String bit : queryStringBits) {
      String[] bitBits = bit.split("=");
      params.put(bitBits[0], bitBits[1]);
    }

    return params;
  }
}