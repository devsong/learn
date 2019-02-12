package com.gzs.learn.hystrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

/**
 * jackson 测试用例
 * @author guanzhisong
 *
 */
public class JacksonTest {
    @Test
    public void testStreamingApi() throws Exception {
        String path = "/tmp/jackson";
        Pojo pojo = new Pojo(1, "Alice");
        JsonFactory factory = new JsonFactory();
        JsonGenerator jsonGenerator = factory.createGenerator(new FileOutputStream(new File(path)));
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", pojo.getId());
        jsonGenerator.writeStringField("name", pojo.getName());
        jsonGenerator.writeEndObject();
        jsonGenerator.close();

        JsonParser parser = factory.createParser(new FileInputStream(new File(path)));
        pojo = convert(parser, Pojo.class);
        System.out.println(pojo);
    }

    @Test
    public void testDataBind() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(System.out, new Pojo(1, "bob"));
    }

    private <T> T convert(JsonParser parser, Class<T> cls) throws Exception {
        T t = cls.newInstance();
        List<String> fields = Stream.of(cls.getDeclaredFields()).map(f -> f.getName()).collect(Collectors.toList());
        if (parser.nextToken() != JsonToken.START_OBJECT) {
            throw new IllegalArgumentException("illegal arg");
        }

        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.currentName();
            parser.nextToken();
            if (fields.contains(fieldName)) {
                Field f = cls.getDeclaredField(fieldName);
                f.setAccessible(true);
                String typeName = f.getGenericType().getTypeName();
                switch (typeName) {
                case "java.lang.Integer":
                case "int":
                    f.setInt(t, parser.getIntValue());
                    break;
                case "java.lang.String":
                    f.set(t, parser.getValueAsString());
                    break;
                }
            }
        }
        parser.close();
        return t;
    }
}

@Data
class Pojo {
    private int id;
    private String name;

    public Pojo() {
    }

    public Pojo(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
