package com.ssafy.trip.algorithm;

public class KMP {

  public static int[] getLPS(String pattern) {
    int patternLen = pattern.length();
    int[] lps = new int[patternLen];
    int j = 0;
    for (int i = 1; i < patternLen; i++) {
      if (pattern.charAt(i) == pattern.charAt(j)) {
        lps[i] = j + 1;
        j++;
      }
    }
    return lps;
  }

  public static boolean match(String text, String pattern, int[] lps) {
    int textLen = text.length();
    int patternLen = lps.length;
    int j = 0;
    for (int i = 0; i < textLen; i++) {
      while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
        j = lps[j - 1];
      }
      if (text.charAt(i) == pattern.charAt(j)) {
        j++;
      }
      if (j == patternLen) {
        return true;
      }
    }
    return false;
  }
}
