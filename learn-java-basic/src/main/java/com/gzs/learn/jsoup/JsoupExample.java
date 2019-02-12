package com.gzs.learn.jsoup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.google.common.collect.Lists;

public class JsoupExample {
    public static final String BASE_URL = "https://www.linuxidc.com/Linux/2010-12/30786%s.htm";

    @Test
    public void testGet() throws IOException {
        FileWriter writer = new FileWriter(new File("/Users/guanzhisong/Desktop/linux.md"));
        List<String> suffix = Lists.newArrayList("");
        for (int i = 2; i < 130; i++) {
            suffix.add("p" + i);
        }
        for(String s : suffix) {
            String url = String.format(BASE_URL, s);
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Elements elem = doc.select("#content > p");
            if (elem == null || elem.isEmpty()) {
                continue;
            }
            Iterator<Element> iterator = elem.iterator();
            boolean titleFlag = false;
            while (iterator.hasNext()) {
                if (!titleFlag) {
                    String title = iterator.next().text();
                    writer.append("### ").append(title);
                    writer.append("\n");
                    titleFlag = true;
                } else {
                    String text = iterator.next().text();
                    writer.append(text).append("\n");
                }
            }
        }
       
        writer.flush();
        writer.close();
    }
}
