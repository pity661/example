package com.wenky.example.utils.transform;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-25 10:24
 */
public class GsonUtil {
  public static final Gson GSON =
      new GsonBuilder()
          .setDateFormat("yyyy-MM-dd HH:mm:ss")
          .disableHtmlEscaping()
          // null属性也会保留
          .serializeNulls()
          // 字符串格式化输出
          .setPrettyPrinting()
          .create();
  public static final Gson GSON_UNDERLINE =
      new GsonBuilder()
          .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
          .setDateFormat("yyyy-MM-dd HH:mm:ss")
          .disableHtmlEscaping()
          .serializeNulls()
          .create();

  // "null" -> null | null -> null
  public static <T> T str2Class(@Nullable String string, Class<T> t) {
    return GSON.fromJson(string, t);
  }

  public static <T> T str2Class(String string, Type type) {
    return GSON.fromJson(string, type);
  }

  public static <T> T str2Class(String string, TypeToken<T> t) {
    return str2Class(string, t.getType());
  }

  public static Map<String, Object> str2Map(String string) {
    return str2Class(string, new TypeToken<HashMap<String, Object>>() {});
  }

  // key -> value
  public static Map<String, Object> obj2Map(@Nullable Object object) {
    return str2Class(obj2String(object), new TypeToken<HashMap<String, Object>>() {});
  }

  // null -> "null" | "null" -> "null"
  public static String obj2String(@Nullable Object object) {
    return GSON.toJson(object);
  }

  public static <T> T obj2TargetObj(@Nonnull Object object, Class<T> t) {
    return str2Class(obj2String(object), t);
  }
}
