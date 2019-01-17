package com.sunnick.easyim.protocol;

import com.alibaba.fastjson.JSON;

/**
 * Created by Sunnick on 2019/1/13/013.
 *
 * json序列化器
 */
public class JsonSerializer implements Serializer {

    public byte getSerializerAlgorithm() {
        return SerializeAlgorithm.json;
    }

    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    public <T> T deSerialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
