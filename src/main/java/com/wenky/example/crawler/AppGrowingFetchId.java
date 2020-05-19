package com.wenky.example.crawler;

import com.alibaba.fastjson.JSONObject;
import com.wenky.example.io.FilePath;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-01 21:20
 */
@Component
public class AppGrowingFetchId {
  @Autowired private RestTemplate restTemplate;

  public void listHandle() throws IOException, InterruptedException {
    //        Integer page = 2288;
    //        Integer page = 3757;
    Integer page = 1500;
    Integer pageSize = 60;
    try (BufferedWriter writer =
        new BufferedWriter(new FileWriter(FilePath.getPath("id-list2.csv")))) {
      List<String> list;
      while ((list = fetchIdList(page, pageSize)) != null) {
        System.out.println(String.format("当前页数:[%s]", page));
        list.stream()
            .forEach(
                id -> {
                  try {
                    writer.write(id);
                    writer.newLine();
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                });
        page++;
      }
      writer.flush();
    }
  }

  public List<String> fetchIdList(Integer page, Integer pageSize) throws InterruptedException {
    Thread.sleep(1000);
    if (page % 1000 == 0) {
      System.out.println(
          "****************满1000个等60分钟****************"
              + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH-mm-ss"));
      Thread.sleep(3606000);
    }
    // https://ds.appgrowing.cn/api/leaflet/mt?channel=105&startDate=2019-10-10&endDate=2020-04-06&order=-updatedAt&isExact=false&page=2&limit=60
    String url =
        "https://ds.appgrowing.cn/api/leaflet/mt?channel=105&startDate=2019-10-10&endDate=2020-04-06&order=-updatedAt&isExact=false&page="
            + page
            + "&limit="
            + pageSize;
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set(
        "cookie",
        "AG_new_version=true; _ga=GA1.2.1168982092.1585880993; YC_NPS_Dialog=true; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221713ddf8c6d629-0d338f9785b7e4-396d7f06-1296000-1713ddf8c6e467%22%2C%22%24device_id%22%3A%221713ddf8c6d629-0d338f9785b7e4-396d7f06-1296000-1713ddf8c6e467%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22https%3A%2F%2Fyoucloud.com%2Fservice%2F%22%2C%22%24latest_referrer_host%22%3A%22youcloud.com%22%2C%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%7D%7D; AG_Token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODYxNTQ2MDQsImlkIjoiMWQ3NDgxZjAtOWVjNi0zMDlmLWI5MTAtYmFiNDM5MTU0MjZmIiwiYWNjIjoyNDQ0MzEsImV4cCI6MTU4ODc0NjYwM30.1hw9DKCzScmO8Miv1u-6SccC8hT7R88yJ_zaP2IIRmM; _gid=GA1.2.1726723271.1586154604; _gat_gtag_UA_4002880_19=1");
    httpHeaders.set(
        "user-agent",
        " Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
    httpHeaders.set("accept-language", "zh-CN,zh;q=0.9");
    //        httpHeaders.set("if-none-match", "W/\"" +
    // UUID.randomUUID().toString().replaceAll("-","").toLowerCase() + "\"");
    httpHeaders.set(
        "referer",
        "https://ds.appgrowing.cn/leaflet?channel=105&startDate=2019-10-07&endDate=2020-04-03&order=-updatedAt&isExact=false&page="
            + page);
    httpHeaders.set("sec-fetch-dest", "empty");
    httpHeaders.set("sec-fetch-mode", "cors");
    httpHeaders.set("sec-fetch-site", "same-origin");
    HttpEntity httpEntity = new HttpEntity(httpHeaders);
    ResponseEntity<String> response =
        restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
    List<String> list = null;
    if (response.getStatusCode() == HttpStatus.OK) {
      try {
        list =
            Optional.ofNullable(response.getBody())
                .map(JSONObject::parseObject)
                .map(json -> json.getJSONArray("data"))
                .map(
                    array ->
                        array.stream()
                            .map(json -> ((JSONObject) json).getString("id"))
                            .collect(Collectors.toList()))
                .orElse(null);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println(
            String.format(
                "请求分页信息返回错误,page:[%s],pageSize:[%s],responseBody:[%s],statusCode:[%s]",
                page, pageSize, response.getBody(), response.getStatusCode()));
      }
    } else {
      System.out.println(
          String.format(
              "请求分页信息返回非200状态码,page:[%s],pageSize:[%s],responseBody:[%s],statusCode:[%s]",
              page, pageSize, response.getBody(), response.getStatusCode()));
    }
    return list;
  }
}
