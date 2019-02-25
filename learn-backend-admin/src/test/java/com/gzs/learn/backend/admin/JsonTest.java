package com.gzs.learn.backend.admin;

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
}

@Data
@AllArgsConstructor
class Foo {
    int id;
    String name;

    public Foo() {

    }
}
