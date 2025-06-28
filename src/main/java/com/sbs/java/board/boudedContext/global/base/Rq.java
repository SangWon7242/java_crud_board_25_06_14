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

  public int getIntParam(String paramName, int defaultValue) {
    if(!params.containsKey(paramName)) return defaultValue;

    try {
      return Integer.parseInt(params.get(paramName));
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue) {
    if(!params.containsKey(paramName)) return defaultValue;

    return params.get(paramName);
  }
}
