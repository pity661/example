package com.wenky.example.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wenky.example.io.FilePath;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
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
    String dateStart = "2020-01-01";
    Date start = DateUtils.parseDate(dateStart, "yyyy-MM-dd");
    String dateEnd = "2020-05-01";
    Date end = DateUtils.parseDate(dateEnd, "yyyy-MM-dd");
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    CountDownLatch countDownLatch = new CountDownLatch(getCount(start, end));
    while (start.before(end)) {
      Date date = start;
      executorService.submit(
          () -> handle(DateFormatUtils.format(date, "yyyy-MM-dd"), 50, countDownLatch));
      start = DateUtils.addDays(start, 1);
    }
    countDownLatch.await();
    System.out.println("跑完了");
  }

  private static int getCount(Date start, Date end) {
    int i = 0;
    while (start.before(end)) {
      start = DateUtils.addDays(start, 1);
      ++i;
    }
    return i;
  }

  public void handle(String date, Integer pageSize, CountDownLatch countDownLatch) {
    // 先全部读取到手机号再进行写入文件操作
    Set<String> mobileSet = new HashSet<>();
    Integer page = 1;
    List<String> list;
    AtomicReference<Integer> count = new AtomicReference<>(0);
    while (!(list = getProductCodeList(date, page, pageSize)).isEmpty()) {
      ++page;
      StopWatch stopWatch = new StopWatch();
      stopWatch.start();
      list.stream()
          .forEach(
              productCode -> {
                String mobile = getPhoneNumber(productCode);
                if (mobile != null
                    && StringUtils.isNotBlank(mobile.trim())
                    && mobile.length() == 11
                    && mobile.startsWith("1")) {
                  mobileSet.add(mobile);
                  count.getAndSet(count.get() + 1);
                }
              });
      stopWatch.stop();
      System.out.println(
          String.format(
              "当前线程:[%s],时间:[%s],页数:[%s],耗时:[%s]",
              Thread.currentThread().getName(), date, page, stopWatch.getTotalTimeSeconds()));
      // 如果重复的数量大于两倍提前结束
      //      if (count.get() > mobileSet.size() * 4) {
      //        System.out.println(
      //            String.format("提前结束。开始写入文件，当前线程:[%s],时间:[%s]", Thread.currentThread().getName(),
      // date));
      //        writerToFile(mobileSet, date, countDownLatch);
      //        break;
      //      }
    }
    if (count.get() <= mobileSet.size() * 3) {
      System.out.println(
          String.format("正常结束。开始写入文件，当前线程:[%s],时间:[%s]", Thread.currentThread().getName(), date));
      writerToFile(mobileSet, date, countDownLatch);
    }
  }

  private void writerToFile(Set<String> mobileSet, String date, CountDownLatch countDownLatch) {
    try (BufferedWriter writer =
        new BufferedWriter(new FileWriter(FilePath.getPath(date + ".csv"), true))) {
      mobileSet.stream()
          .forEach(
              mobile -> {
                try {
                  writer.write(mobile);
                  writer.newLine();
                } catch (IOException e) {
                  e.printStackTrace();
                }
              });
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("写入文件结束了: " + date);
    countDownLatch.countDown();
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

  public static void main(String[] args) throws ParseException {
    // 1
    //        String s = "dat_source_type%3D1%26page%3D" + 2 +
    // "%26key%3D83d0ec7c04343a285cad621fa0ddcaea";
    //        List<String> list = new ArrayList<>();
    //        for (int i=0;i<s.length();i++) {
    //            int value = s.charAt(i);
    //            list.add(String.valueOf(value));
    //        }
    //        String result = list.stream().collect(Collectors.joining(","));
    //        System.out.println(DigestUtils.md5Hex(result));
    // 2
    //    Integer a = Long.valueOf(new Date().getTime() / 1000).intValue();
    //    Integer b = Double.valueOf(Math.random() * 9999).intValue();
    //    System.out.println(a);
    //    System.out.println(b);
    // 3
    String dateStart = "2020-01-01";
    Date start = DateUtils.parseDate(dateStart, "yyyy-MM-dd");
    String dateEnd = "2020-05-01";
    Date end = DateUtils.parseDate(dateEnd, "yyyy-MM-dd");
    System.out.println(getCount(start, end));
  }
}
