package com.wenky.example.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wenky.example.io.FilePath;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-03-26 17:19
 */
@Component
public class ErLangCha {

  @Autowired private RestTemplate restTemplate;

  public List<String> getProductCodeList(Integer page, Integer pageSize) {
    String url =
        "https://www.erlangcha.com/api/list?page="
            + page
            + "&pageList="
            + pageSize
            + "&dat_source_type=1";
    ResponseEntity<String> response =
        restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    return Optional.ofNullable(response)
        .map(ResponseEntity::getBody)
        .map(JSON::parseObject)
        .map(json -> json.getJSONObject("data"))
        .map(jsonObject -> jsonObject.getJSONArray("content"))
        .map(
            array ->
                array.stream()
                    .map(json -> ((JSONObject) json).getString("product_code"))
                    .collect(Collectors.toList()))
        .orElse(null);
  }

  public String getPhoneNumber(String productCode) {
    String url = "https://ec.snssdk.com/product/lubanajaxstaticitem?id=" + productCode;
    ResponseEntity<String> response =
        restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    return Optional.ofNullable(response)
        .map(ResponseEntity::getBody)
        .map(JSON::parseObject)
        .map(json -> json.getJSONObject("data"))
        .map(json -> json.getString("mobile"))
        .orElse(null);
  }

  public void handle() throws IOException, InterruptedException {
    try (BufferedWriter writer =
        new BufferedWriter(new FileWriter(FilePath.getPath("2020-04-14.csv"), true))) {
      Integer page = 958;
      Integer pageSize = 200;
      List<String> list;
      while (!(list = getProductCodeList(page, pageSize)).isEmpty()) {
        System.out.println("page:" + page);
        ++page;
        list.stream()
            .forEach(
                productCode -> {
                  String mobile = getPhoneNumber(productCode);
                  if (StringUtils.isNotBlank(mobile)) {
                    mobile = mobile.trim();
                  }
                  if (StringUtils.isNotBlank(mobile)
                      && mobile.length() == 11
                      && mobile.startsWith("1")) {
                    try {
                      writer.write(mobile);
                      writer.newLine();
                    } catch (IOException e) {
                      e.printStackTrace();
                    }
                  }
                });
        writer.flush();
      }
    }
  }
}
