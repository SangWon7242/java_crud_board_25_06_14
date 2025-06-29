package com.sbs.java.board.boudedContext.global.interceptor;

import com.sbs.java.board.boudedContext.global.base.Rq;

public interface Interceptor {
  boolean run(Rq rq);
}
