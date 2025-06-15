package com.sbs.java.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
  public static void main(String[] args) {
    String queryString = "f=5&a=1&b=2&c=3&d=4";
    String[] queryStringBits = queryString.split("&");
    System.out.println(Arrays.toString(queryStringBits));

    List<String> paramNames = new ArrayList<>();
    List<Integer> paramValues = new ArrayList<>();

    for (String bit : queryStringBits) {
      String[] bitBits = bit.split("=");
      String paramName = bitBits[0];
      String paramValue = bitBits[1];

      paramNames.add(paramName);
      paramValues.add(Integer.valueOf(paramValue));
    }

    for(int i = 0; i < paramNames.size(); i++) {
      String paramName = paramNames.get(i);
      int paramValue = paramValues.get(i);

      System.out.printf("%s : %d\n", paramName, paramValue);
    }

    // d의 위치 확인
    int findIndex = paramNames.indexOf("d");
    System.out.println("d의 값 : " + paramValues.get(findIndex));
  }
}
