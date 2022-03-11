package com.wenky.example.utils.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-06-03 16:13
 */
public class ExcelAnalysisResult {
    // rulesetid_名称
    private String sheetName;
    private String sysDicDataKey;
    private String sysDicDataName;
    private List<RuleInfo> ruleInfoList;

    public ExcelAnalysisResult initData(String sheetName, List<List<String>> dataList) {
        this.sheetName = sheetName;
        List<String> firstList = dataList.get(0);
        this.sysDicDataKey = firstList.get(3);
        this.sysDicDataName = firstList.get(2);
        this.ruleInfoList = new ArrayList<>();
        for (int i = 1; i < dataList.size(); i++) {
            List<String> list = dataList.get(i);
            if (list.get(14).contains(",")) {
                Stream.of(list.get(14).split(","))
                        .forEach(
                                exceptValue -> {
                                    list.set(14, exceptValue);
                                    ruleInfoList.add(new RuleInfo(list));
                                });
            } else {
                ruleInfoList.add(new RuleInfo(list));
            }
        }
        return this;
    }

    public String getSysDicDataKey() {
        return sysDicDataKey;
    }

    public String getSysDicDataName() {
        return sysDicDataName;
    }

    public List<RuleInfo> getRuleInfoList() {
        return ruleInfoList;
    }

    public String getRuleSetId() {
        return Optional.ofNullable(sheetName).map(name -> name.split("_")[0]).orElse(sheetName);
    }

    public String getRuleName() {
        return sheetName != null && sheetName.contains("_") ? sheetName.split("_")[1] : sheetName;
    }

    private class RuleInfo {
        private String ruleType;
        private String riskType;
        private String hitResult;
        private String riskLevel;
        private Integer weightScore;
        private List<SysDicDataInfo> sysDicDataInfoList;
        private String ruleKey;
        private String exceptValue;

        public RuleInfo(List<String> list) {}

        public String getRuleType() {
            return ruleType;
        }

        public String getRiskType() {
            return riskType;
        }

        public String getHitResult() {
            return hitResult;
        }

        public String getRiskLevel() {
            return riskLevel;
        }

        public Integer getWeightScore() {
            return weightScore;
        }

        public List<SysDicDataInfo> getSysDicDataInfoList() {
            return sysDicDataInfoList;
        }

        public String getRuleKey() {
            return ruleKey;
        }

        public String getExceptValue() {
            return exceptValue;
        }
    }

    private class SysDicDataInfo {
        // (字段变量)规则编码
        private String key;
        // 规则名称
        private String name;
        // 数字类型
        private String type;

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }
}
