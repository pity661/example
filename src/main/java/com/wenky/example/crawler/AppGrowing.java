package com.wenky.example.crawler;

import com.alibaba.fastjson.JSONObject;
import com.wenky.example.io.FilePath;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-01 16:26
 */
@Component
public class AppGrowing {
    /** appgrowing.cn 账号：17767078872 密码：adzqcm2020 */
    @Autowired private RestTemplate restTemplate;

    private static String cookie =
            "sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221713562452bc89-0f8b72b7aee6d8-396d7f06-1296000-1713562452c85c%22%2C%22%24device_id%22%3A%221713562452bc89-0f8b72b7aee6d8-396d7f06-1296000-1713562452c85c%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; _ga=GA1.2.1662432266.1585738565; _gid=GA1.2.541004223.1585738565; AG_Token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODU3Mzg1NzcsImlkIjoiMWQ3NDgxZjAtOWVjNi0zMDlmLWI5MTAtYmFiNDM5MTU0MjZmIiwiYWNjIjoyNDQ0MzEsImV4cCI6MTU4ODMzMDU3N30.c16tTk3q7TZOauNFyJSNH68Z1gq4Dte6exrER8M4wJs; YC_NPS_Dialog=true";
    private static String cookie_1 =
            "sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221713562452bc89-0f8b72b7aee6d8-396d7f06-1296000-1713562452c85c%22%2C%22%24device_id%22%3A%221713562452bc89-0f8b72b7aee6d8-396d7f06-1296000-1713562452c85c%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; _ga=GA1.2.1662432266.1585738565; _gid=GA1.2.541004223.1585738565; AG_Token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODU3Mzg1NzcsImlkIjoiMWQ3NDgxZjAtOWVjNi0zMDlmLWI5MTAtYmFiNDM5MTU0MjZmIiwiYWNjIjoyNDQ0MzEsImV4cCI6MTU4ODMzMDU3N30.c16tTk3q7TZOauNFyJSNH68Z1gq4Dte6exrER8M4wJs; YC_NPS_Dialog=true; _gat_gtag_UA_4002880_19=1";

    public static HttpEntity fetchEntity(String cookie, String referer, String site) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("cookie", cookie);

        httpHeaders.set(
                "user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");

        httpHeaders.set("accept-language", "zh-CN,zh;q=0.9");
        httpHeaders.set(
                "if-none-match",
                "W/\"" + UUID.randomUUID().toString().replaceAll("-", "").toLowerCase() + "\"");
        httpHeaders.set("referer", referer);
        httpHeaders.set("sec-fetch-dest", "empty");
        httpHeaders.set("sec-fetch-mode", "cors");
        httpHeaders.set("sec-fetch-site", site);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return httpEntity;
    }

    public void preCheck(String cookie, String referer) {
        String url = "https://auth.appgrowing.cn/api/auth/userinfo";
        ResponseEntity<String> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        fetchEntity(cookie, referer, "same-site"),
                        String.class);
        String url1 = "https://ds.appgrowing.cn/api/nps";
        ResponseEntity<String> response1 =
                restTemplate.exchange(
                        url1,
                        HttpMethod.GET,
                        fetchEntity(cookie, referer, "same-origin"),
                        String.class);
    }

    public List<String> fetchIdList(Integer page, Integer pageSize) {
        String url = "http://127.0.0.1:9999/example/test";
        //    String url =
        //
        // "https://ds.appgrowing.cn/api/leaflet/mt?channel=105&startDate=2019-10-05&endDate=2020-04-01&order=-updatedAt&isExact=false&page="
        //            + page
        //            + "&limit="
        //            + pageSize;
        String referer =
                "https://ds.appgrowing.cn/leaflet?channel=105&startDate=2019-10-05&endDate=2020-04-01&order=-updatedAt&isExact=false&page="
                        + page;
        if (page == 1) {
            preCheck(cookie, referer);
        }
        ResponseEntity<String> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        fetchEntity(cookie, referer, "same-origin"),
                        String.class);
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
                                                        .map(
                                                                json ->
                                                                        ((JSONObject) json)
                                                                                .getString("id"))
                                                        .collect(Collectors.toList()))
                                .orElse(null);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(
                        String.format(
                                "请求分页信息返回错误,page:[%s],pageSize:[%s],responseBody:[%s],statusCode:[%s]",
                                page, pageSize, response.getBody(), response.getStatusCode()));
            }
        }
        System.out.println(
                String.format(
                        "请求分页信息返回非200状态码,page:[%s],pageSize:[%s],responseBody:[%s],statusCode:[%s]",
                        page, pageSize, response.getBody(), response.getStatusCode()));
        return list;
    }

    public String fetchRedirectUrl(String id) {
        String referer = "https://ds.appgrowing.cn/leaflet/" + id;
        String url = "https://ds.appgrowing.cn/api/leaflet/detail?page=1&limit=1&id=" + id;
        preCheck(cookie, referer);
        ResponseEntity<String> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        fetchEntity(cookie, referer, "same-origin"),
                        String.class);
        String redirectUrl = null;
        try {
            redirectUrl =
                    Optional.ofNullable(response.getBody())
                            .map(JSONObject::parseObject)
                            .map(json -> json.getJSONArray("data"))
                            .map(
                                    array ->
                                            array.stream()
                                                    .map(
                                                            json ->
                                                                    ((JSONObject) json)
                                                                            .getString("redirect"))
                                                    .findFirst()
                                                    .orElse(null))
                            .orElse(null);
        } catch (Exception e) {
            System.out.println(
                    String.format(
                            "请求详情页返回错误,id:[%s],responseBody:[%s],statusCode:[%s]",
                            id, response.getBody(), response.getStatusCode()));
            e.printStackTrace();
        }
        return redirectUrl;
    }

    public void handle() throws IOException, InterruptedException {
        Integer page = 1;
        Integer pageSize = 60;
        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(FilePath.getPath("redirect.csv")))) {
            List<String> list;
            while ((list = fetchIdList(page, pageSize)) != null) {
                System.out.println(String.format("当前页数:[%s]", page));
                list.stream()
                        .forEach(
                                id -> {
                                    try {
                                        Thread.sleep(200);
                                        writer.write(fetchRedirectUrl(id));
                                        writer.newLine();
                                    } catch (IOException | InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                });
                Thread.sleep(5000);
                page++;
            }
            writer.flush();
        }
    }

    public static void main(String[] args) {
        String s = "W/\"" + UUID.randomUUID().toString().toLowerCase() + "\"";
        System.out.println(s);
    }
}
