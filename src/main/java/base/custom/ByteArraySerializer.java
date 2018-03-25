package base.custom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Custom byte array serializer using java serialization
 */
public class ByteArraySerializer implements Serializer<Serializable> {

    /**
     * Serialize to byte array
     * @param value - serializable type
     * @return byte array
     * @throws IOException
     */
    public byte[] serialize(Serializable value) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(value);
        oos.flush();
        byte [] data = bos.toByteArray();
        return data;
    }
}
