package base.custom;

import java.io.IOException;

/**
 * Custom serializer
 * @param <T> - serializable type
 */
public interface Serializer<T> {

    /**
     * Serialize to byte array
     * @param value - serializable type
     * @return byte array
     * @throws IOException
     */
     byte[] serialize(T value) throws IOException;

}
