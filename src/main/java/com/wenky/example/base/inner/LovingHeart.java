package com.wenky.example.base.inner;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-10-21 18:24
 */
public class LovingHeart {
  public static void main(String[] args) {
    for (float y = 1.5f; y >= -1.5f; y -= 0.1f) {
      for (float x = -1.5f; x <= 1.5f; x += 0.05f) {
        float a = x * x + y * y - 1;
        if (a * a * a - x * x * y * y * y <= 0.0f) System.out.print("*");
        else System.out.print(" ");
      }
      System.out.println();
    }
  }
}
