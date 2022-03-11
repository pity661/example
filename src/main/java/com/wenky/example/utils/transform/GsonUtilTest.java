package com.wenky.example.utils.transform;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-25 11:24
 */
public class GsonUtilTest {
    public JsonObject createJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("aa", "aa");
        jsonObject.addProperty("bb", Boolean.FALSE);
        jsonObject.addProperty("cc", Integer.MAX_VALUE);
        jsonObject.addProperty("dd", 'a');
        return jsonObject;
    }

    public static JsonArray createJsonArray() {
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("aa", "aa");
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("aa", "aa");
        JsonObject jsonObject3 = new JsonObject();
        jsonObject3.add("aa", jsonObject2);
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject3);
        return jsonArray;
    }

    public static void transform() {
        People people = new People();
        people.setAddress("aa");
        people.setAge(11);
        people.setName("wenky");
        System.out.println(GsonUtil.obj2String(people));

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("NamE", "NamE");
        jsonObject.addProperty("Name", "Name");
        jsonObject.addProperty("name", "name");
        People people1 = GsonUtil.obj2TargetObj(jsonObject, People.class);
        System.out.println(GsonUtil.obj2String(people1));
    }

    public static void main(String[] args) {
        transform();
    }

    public static class People {
        // 1.对象转化为字符串时 key会对应value的值
        // 2.字符串转化为对象时 value的权重最低，alternate最后一个属性权重最高
        @SerializedName(
                value = "NamE",
                alternate = {"Name", "NAME"})
        private String name;

        private Integer age;
        // transient 修饰字段不会被序列化
        private transient String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
