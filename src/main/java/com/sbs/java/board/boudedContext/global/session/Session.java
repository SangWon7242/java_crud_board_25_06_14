package com.sbs.java.board.boudedContext.global.session;

import java.util.HashMap;
import java.util.Map;

public class Session {
  Map<String, Object> sessionData;

  public Session() {
    sessionData = new HashMap<>();
  }

  // 세션은 데이터를 저장, 조회, 삭제, 존재 유무하는 기능을 제공
  // 저장 : setAttribute
  // 조회 : getAttribute
  // 삭제 : removeAttribute
  // 존재 유무 : hasAttribute

  public void setAttribute(String key, Object value) {
    sessionData.put(key, value);
  }

  public Object getAttribute(String key) {
    return sessionData.get(key);
  }

  public void removeAttribute(String key) {
    sessionData.remove(key);
  }

  public boolean hasAttribute(String key) {
    return sessionData.containsKey(key);
  }
}
