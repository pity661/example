package com.wenky.example.express;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author 克林
 * @date 2024/3/19 14:21
 */
@Component
public class ExpressQuery {

    @Autowired
    private RestTemplate restTemplate;

    public void exec() {
        String url =
                "https://alayn.baidu.com/express/appdetail/pc_express?query_from_srcid=51150&tokenV2=ckwAJ4CLpSxqp7HPrYPhUWnZItcm%2BX4zRmALgi5DP023sd%2Bw31J5PzWKeiYR%2FypC&nu=78398110603501&qid=ea56551f0000f8a3&cb=jsonp_1710831360000_60750";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", "BAIDUID_BFESS=95F776C0B92E748FDF01334326B54F5E:FG=1; BIDUPSID=95F776C0B92E748FDF01334326B54F5E; PSTM=1710831360; ZFY=malIkOtrFXYrdwTqRtIFoK14yWNV:AyTEcFpBav:AU3mg:C; MCITY=-179%3A; delPer=0; BCLID=766316734721340678; BCLID_BFESS=766316734721340678; BDSFRCVID=Uc_OJeC62mPknO7qn2LfEHtYoNMHCZjTH6aoL5maSwgnzYkitEnJEG0PBf8g0KubgKDaogKKKgOTHI6Fc2L2sDn2nNEClua76-i_tf8g0f5; BDSFRCVID_BFESS=Uc_OJeC62mPknO7qn2LfEHtYoNMHCZjTH6aoL5maSwgnzYkitEnJEG0PBf8g0KubgKDaogKKKgOTHI6Fc2L2sDn2nNEClua76-i_tf8g0f5; H_BDCLCKID_SF=tJkqoI8-JDt3fP36q4QSMJt_-q-X5-CsQC7d2hcH0KLKDfJchxPbKt6XXMj4Bn3ka2ouapOu2fb1MRjvX5oV-qtWbUTEX4n0bgTXaq5TtUJIeCnTDMRh-4-UQMQyKMniWKv9-pnYJqQjhl44KUO_D4CNQtbQ-4_fJtj0M-bDfn02eCKu-n5jHjJQjHKO3f; H_BDCLCKID_SF_BFESS=tJkqoI8-JDt3fP36q4QSMJt_-q-X5-CsQC7d2hcH0KLKDfJchxPbKt6XXMj4Bn3ka2ouapOu2fb1MRjvX5oV-qtWbUTEX4n0bgTXaq5TtUJIeCnTDMRh-4-UQMQyKMniWKv9-pnYJqQjhl44KUO_D4CNQtbQ-4_fJtj0M-bDfn02eCKu-n5jHjJQjHKO3f; H_PS_PSSID=39662_40207_40080_40364_40352_40299_40373_40366_40403_40415_40458_40456; BA_HECTOR=a5802k21a5a10l208h8la184fj15t31ivic6s1s; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; PSINO=5");
        httpHeaders.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(response);
    }
}
