package com.wenky.example.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wenky.example.io.FilePath;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-08-03 18:17
 */
@Component
public class ErlangChaV2 {
  @Autowired private RestTemplate restTemplate;

  public void cookieTest() {
    ResponseEntity<String> response =
        restTemplate.exchange("https://www.erlangcha.com/", HttpMethod.GET, null, String.class);
    System.out.println(response.getHeaders());
  }

  public List<String> getProductCodeList(String date, Integer page, Integer pageSize) {
    String url =
        "https://www.erlangcha.com/api/getShopList?page="
            + page
            + "&dat_source_type=1"
            + "&online_start_time="
            + date
            + "&online_end_time="
            + date
            + "&pageList="
            + pageSize;
    HttpHeaders httpHeaders = getHttpHeaders(page, date, pageSize);
    HttpEntity httpEntity = new HttpEntity(httpHeaders);
    ResponseEntity<String> response =
        restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
    List<String> list =
        Optional.ofNullable(response)
            .map(ResponseEntity::getBody)
            .map(JSON::parseObject)
            .map(json -> json.getJSONObject("data"))
            .map(jsonObject -> jsonObject.getJSONArray("data"))
            .map(
                array ->
                    array.stream()
                        .map(json -> ((JSONObject) json).getString("shop_link"))
                        .map(link -> link.split("\\?id=")[1])
                        .collect(Collectors.toList()))
            .orElse(new ArrayList<>());
    if (list.isEmpty()) {
      System.out.println(response);
    }
    return list;
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

  public void handle() throws ParseException, InterruptedException {
    String dateStart = "2020-05-01";
    Date start = DateUtils.parseDate(dateStart, "yyyy-MM-dd");
    String dateEnd = "2020-08-03";
    Date end = DateUtils.parseDate(dateEnd, "yyyy-MM-dd");
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    CountDownLatch countDownLatch = new CountDownLatch(94);
    int i = 0;
    while (start.before(end)) {
      Date date = start;
      executorService.submit(
          () -> {
            try {
              handle(DateFormatUtils.format(date, "yyyy-MM-dd"), 50, countDownLatch);
            } catch (IOException e) {
              e.printStackTrace();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          });
      start = DateUtils.addDays(start, 1);
      i++;
      if (!start.before(end)) {
        System.out.println(i);
      }
    }
    countDownLatch.await();
  }

  public void handle(String date, Integer pageSize, CountDownLatch countDownLatch)
      throws IOException, InterruptedException {
    try (BufferedWriter writer =
        new BufferedWriter(new FileWriter(FilePath.getPath(date + ".csv"), true))) {
      Integer page = 1;
      List<String> list;
      while (!(list = getProductCodeList(date, page, pageSize)).isEmpty()) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
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
        stopWatch.stop();
        System.out.println(
            String.format(
                "当前线程:[%s],时间:[%s],页数:[%s],耗时:[%s]",
                Thread.currentThread().getName(), date, page, stopWatch.getTotalTimeSeconds()));
        writer.flush();
      }
      System.out.println("结束了: " + date);
      countDownLatch.countDown();
    }
  }

  private HttpHeaders getHttpHeaders(Integer page, String date, Integer pageSize) {
    String csrf = "cabbd66dd58ccf36c5e3119685ef39eb";
    String KEY = "83d0ec7c04343a285cad621fa0ddcaea";
    // "dat_source_type%3D1
    // %26online_end_time%3D2020-08-01
    // %26online_start_time%3D2020-08-01
    // %26page%3D3
    // %26pageList%3D50
    // %26key%3D83d0ec7c04343a285cad621fa0ddcaea"
    String s =
        "dat_source_type%3D1"
            + "%26online_end_time%3D"
            + date
            + "%26online_start_time%3D"
            + date
            + "%26page%3D"
            + page
            + "%26pageList%3D"
            + pageSize
            + "%26key%3D"
            + KEY;
    List<String> list = new ArrayList<>();
    for (int i = 0; i < s.length(); i++) {
      int value = s.charAt(i);
      list.add(String.valueOf(value));
    }
    String result = list.stream().collect(Collectors.joining(","));

    Integer a = Long.valueOf(new Date().getTime() / 1000).intValue();
    Integer b = Double.valueOf(Math.random() * 9999).intValue();

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Keep-Mt", b.toString());
    httpHeaders.set("Keep-At", a.toString());
    httpHeaders.set("Csrf-Sign", DigestUtils.md5Hex(b.toString() + a.toString() + csrf));
    httpHeaders.set("Keep-Csrf", csrf);
    httpHeaders.set("sign", DigestUtils.md5Hex(result));
    return httpHeaders;
  }

  public static void main(String[] args) {
    //        String s = "dat_source_type%3D1%26page%3D" + 2 +
    // "%26key%3D83d0ec7c04343a285cad621fa0ddcaea";
    //        List<String> list = new ArrayList<>();
    //        for (int i=0;i<s.length();i++) {
    //            int value = s.charAt(i);
    //            list.add(String.valueOf(value));
    //        }
    //        String result = list.stream().collect(Collectors.joining(","));
    //        System.out.println(DigestUtils.md5Hex(result));

    Integer a = Long.valueOf(new Date().getTime() / 1000).intValue();
    Integer b = Double.valueOf(Math.random() * 9999).intValue();
    System.out.println(a);
    System.out.println(b);
  }
}
