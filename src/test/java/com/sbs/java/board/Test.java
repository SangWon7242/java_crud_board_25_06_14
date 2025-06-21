package com.sbs.java.board;

import java.util.HashMap;
import java.util.Map;

public class Test {
  public static void main(String[] args) {
    String url = "/usr/article/list?page=1&searchKeyword=제목1&searchKeywordTypeCode=subject&boardId=1";
    Map<String, String> params = Util.getParamsFormUrl(url);
    System.out.println(params);

    String urlPath = Util.getPathFromUrl(url);
    System.out.println(urlPath);
  }
}

class Util {
  static Map<String, String> getParamsFormUrl(String url) {
    Map<String, String> params = new HashMap<>();
    String[] urlBits = url.split("\\?", 2);

    if(urlBits.length == 1) return params;

    String queryStr = urlBits[1];

    for(String bit : queryStr.split("&")) {
      String[] bits = bit.split("=", 2);

      if(bits.length == 1) continue;

      params.put(bits[0], bits[1]);
    }

    return params;
  }

  static String getPathFromUrl(String url) {
    return url.split("\\?", 2)[0];
  }
}