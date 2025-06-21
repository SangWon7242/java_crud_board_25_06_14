package com.sbs.java.board.boudedContext.global.base;

import com.sbs.java.board.boudedContext.global.standard.Ut;

import java.util.Map;

public class Rq {
  public String url;
  public Map<String, String> params;
  public String urlPath;

  public Rq(String url) {
    this.url = url;
    params =  Ut.getParamsFormUrl(url);
    urlPath = Ut.getPathFromUrl(url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String urlPath() {
    return urlPath;
  }
}
