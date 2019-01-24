package com.sunnick.easyim.protocol;

/**
 * Created by Sunnick on 2019/1/13/013.
 *
 * Serializer，用来指定序列化算法，用于序列化对象
 */
public interface Serializer {


    /**
     * @return 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * 将对象序列化成二进制
     *
     */
    byte[] serialize(Object object);

    /**
     * 将二进制反序列化为对象
     */
    <T> T deSerialize(Class<T> clazz, byte[] bytes);
}
