package com.gzs.learn.springboot.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    public static Map<Integer, User> datas = new HashMap<>();
    static {
        for (int i = 0; i < 10; i++) {
            datas.put(i, new User(i, "test" + i));
        }
    }
    int id;
    String name;

    protected User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
