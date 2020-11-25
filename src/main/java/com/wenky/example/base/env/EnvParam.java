package com.wenky.example.base.env;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-09 16:18
 */
public class EnvParam {
  public static void main(String[] args) {
    for (String arg : args) {
      System.out.println(arg);
    }
    String VM_PARAM = System.getProperty("VM_PARAM");
    String VM_PARAM1 = System.getProperty("VM_PARAM1");
    System.out.println(String.format("property: VM_PARAM[%s], VM_PARAM1[%s]", VM_PARAM, VM_PARAM1));

    String ENV_PARAM = System.getenv("ENV_PARAM");
    String ENV_PARAM1 = System.getenv("ENV_PARAM1");
    System.out.println(String.format("env: ENV_PARAM[%s], ENV_PARAM1[%s]", ENV_PARAM, ENV_PARAM1));
    System.out.println(System.getProperty("arg2"));
    System.out.println(System.getenv("arg2"));
  }
}
