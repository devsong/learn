package com.gzs.learn.backend.admin;

import java.util.Base64;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

import lombok.AllArgsConstructor;
import lombok.Data;

public class JsonTest {

    @Test
    public void testJsonProp() {
        Foo foo = new Foo(1, "test");
        System.out.println(JSON.toJSONString(foo));

        String json = "{\"id\":1,\"name\":\"test\",\"name1\":111}";
        foo = JSON.parseObject(json, Foo.class, Feature.IgnoreNotMatch);
        System.out.println(foo);
    }

    @Test
    public void testBase64() {
        System.out.println(new String(org.apache.shiro.codec.Base64.decode("3AvVhmFLUs0KTA3Kprsdag==")));
        System.out.println(new String(Base64.getDecoder().decode("3AvVhmFLUs0KTA3Kprsdag==")));
    }
}

@Data
@AllArgsConstructor
class Foo {
    int id;
    String name;

    public Foo() {

    }
}
