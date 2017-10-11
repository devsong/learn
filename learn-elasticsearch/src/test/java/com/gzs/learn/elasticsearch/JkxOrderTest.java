package com.gzs.learn.elasticsearch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.gzs.learn.elasticsearch.domain.JkxOrder;
import com.gzs.learn.elasticsearch.repository.JkxRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JkxOrderTest {
    @Autowired
    private JkxRepository jkxRepository;

    @Test
    public void testFindById() {
        final String orderId = "J0134733074244323";
        final JkxOrder order = jkxRepository.findOne(orderId);
        Assert.assertNotNull(order);
        System.out.println(JSON.toJSONString(order));
    }

    @Test
    public void testUpdateById() {
        final String orderId = "J0134733074244323";
        final JkxOrder order = jkxRepository.findOne(orderId);
        order.setDeviceId(83);
        jkxRepository.save(order);
        Assert.assertNotNull(order);
        System.out.println(JSON.toJSONString(order));
    }


    @Test
    public void testSerializable() throws IOException {
        A a = new A(1, "234");
        ObjectOutputStream os =
                new ObjectOutputStream(new FileOutputStream(new File("/tmp/test.obj")));
        os.writeObject(a);
        os.flush();
        os.close();
    }

    @Test
    public void testUnSerializable() throws Exception {
        ObjectInputStream in =
                new ObjectInputStream(new FileInputStream(new File("/tmp/test.obj")));
        Object obj = in.readObject();
        in.close();
        System.out.println(JSON.toJSONString(obj));
    }
}


class A implements Serializable {
    // private static final long serialVersionUID = 1L;
    int a;
    String b;
    // String c;

    public A(int a, String b) {
        this.a = a;
        this.b = b;
    }
}
