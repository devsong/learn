package com.gzs.learn.backend.admin.entity;

import org.junit.Test;

import lombok.Data;

public class TestClass {

    @Test
    public void testTryCatch() {
        Foo ret = null;
        try {
            ret = createFoo();
        } catch (Exception e) {
            assignObj(ret);
        }
        assignObj(ret);
        System.out.println(ret.getName());
    }

    public Foo createFoo() throws Exception{
        throw new Exception();
        //return new Foo("try");
    }

    public void assignObj(Foo foo) {
        foo = new Foo("assign");
    }
}

@Data
class Foo {
    private String name;

    public Foo(String name) {
        this.name = name;
    }
}
