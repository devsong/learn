package com.gzs.learn.patterdesign.structure.adapter;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import lombok.Data;

@Data
public class DataConvert {

    public String toJsonData(DataObject dataObject) {
        return JSON.toJSONString(dataObject);
    }

    public String toXmlData(DataObject dataObject) {
        XStream xs = new XStream();
        return xs.toXML(dataObject);
    }

    public DataObject convertFromXml(String xml) {
        XStream xs = new XStream();
        DataObject d = (DataObject) xs.fromXML(xml);
        return d;
    }

    public DataObject convertFromJson(String json) {
        DataObject data = JSON.parseObject(json, DataObject.class);
        return data;
    }

    public static void main(String[] args) {
        DataObject dataObject = new DataObject(1, "test");
        DataConvert convert = new DataConvert();
        String json = convert.toJsonData(dataObject);
        dataObject = convert.convertFromJson(json);
        String xml = convert.toXmlData(dataObject);
        System.out.println(xml);
    }
}
