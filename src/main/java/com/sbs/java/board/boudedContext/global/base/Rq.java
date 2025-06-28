package com.sbs.java.board.boudedContext.global.base;

import com.sbs.java.board.boudedContext.global.standard.Ut;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class Rq {
  @Getter
  public String url;
  @Getter
  public Map<String, String> params;
  @Getter
  public String urlPath;

  @Getter
  @Setter
  String controllerTypeCode;

  @Getter
  @Setter
  String controllerName;

  @Getter
  @Setter
  String actionMethodName;

  public Rq(String url) {
    this.url = url;
    params =  Ut.getParamsFormUrl(url);
    urlPath = Ut.getPathFromUrl(url);
  }


  public String getActionPath() {
    String[] commandBits = urlPath.split("/");

    // /usr/article/write
    // ['', 'usr', 'article', 'write']

    controllerTypeCode = commandBits[1];
    controllerName = commandBits[2];
    actionMethodName = commandBits[3];

    return "/%s/%s/%s".formatted(controllerTypeCode, controllerName, actionMethodName);
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
