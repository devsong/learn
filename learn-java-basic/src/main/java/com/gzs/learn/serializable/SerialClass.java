package com.gzs.learn.serializable;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.alibaba.fastjson.JSON;

import lombok.Data;

@Data
public class SerialClass implements Externalizable {
    private int a;
    private String name;

    public SerialClass() {

    }

    public SerialClass(int a, String name) {
        this.a = a;
        this.name = name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        String str = JSON.toJSONString(this);
        out.write(str.getBytes());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[1024];
        in.read(buffer);
        String str = new String(buffer, 0, buffer.length);
        SerialClass obj = JSON.parseObject(str, SerialClass.class);
        this.a = obj.getA();
        this.name = obj.getName();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialClass obj = new SerialClass(1, "test");
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/tmp/obj"));
        os.writeObject(obj);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("/tmp/obj"));
        SerialClass objSerial = (SerialClass) in.readObject();
        System.out.println(objSerial.getA() + " " + objSerial.getName());
        os.close();
        in.close();
    }
}
