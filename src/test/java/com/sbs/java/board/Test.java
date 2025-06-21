package com.sbs.java.board;

import java.util.HashMap;
import java.util.Map;

public class Test {
  public static void main(String[] args) {
    Rq rq = new Rq("/usr/article/list?page=1&searchKeyword=제목1&searchKeywordTypeCode=subject&boardId=1");
    Map<String, String> params = rq.getParams();
    System.out.println(params);
    System.out.println(rq.getParams());
    System.out.println(rq.getParams());

    String urlPath = rq.urlPath();
    System.out.println(urlPath);
    System.out.println(rq.urlPath());
    System.out.println(rq.urlPath());
  }
}

class Rq {
  String url;
  Map<String, String> params;
  String urlPath;

  Rq(String url) {
    this.url = url;
  }

  public Map<String, String> getParams() {
    if(params == null) {
      params = Util.getParamsFormUrl(url);
    }

    return params;
  }

  public String urlPath() {
    if(urlPath == null) {
      urlPath = Util.getPathFromUrl(url);
    }

    return urlPath;
  }
}

class Util {
  static Map<String, String> getParamsFormUrl(String url) {
    System.out.println("getParamsFormUrl 실행됨");
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
    System.out.println("getPathFromUrl 실행됨");
    return url.split("\\?", 2)[0];
  }
}