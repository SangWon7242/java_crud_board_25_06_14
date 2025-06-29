package com.sbs.java.board.boudedContext.global.base;

import com.sbs.java.board.boudedContext.global.containerr.Container;
import com.sbs.java.board.boudedContext.global.session.Session;
import com.sbs.java.board.boudedContext.global.standard.Ut;
import com.sbs.java.board.boudedContext.member.dto.Member;
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

  Session session;
  String loginedMember;

  public Rq() {
    session = Container.session;
    loginedMember = "loginedMember";
  }

  public void setCommand(String url) {
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
  
  // 로그인 여부 확인
  public boolean isLogined() {
    return session.hasAttribute(loginedMember);
  }

  // 로그아웃 여부 확인
  public boolean isLogout() {
    return !isLogined();
  }

  public void setSessionAttr(String key, Object value) {
    session.setAttribute(key, value);
  }

  public Object getSessionAttr(String key) {
    return session.getAttribute(key);
  }

  public boolean hasSessionAttr(String key) {
    return session.hasAttribute(key);
  }

  public void removeSessionAttr(String key) {
    session.removeAttribute(key);
  }

  public void login(Member member) {
    setSessionAttr(loginedMember, member);
  }

  public void logout() {
    removeSessionAttr(loginedMember);
  }

  public Member getLoginedMember() {
    return (Member) getSessionAttr(loginedMember);
  }
}
