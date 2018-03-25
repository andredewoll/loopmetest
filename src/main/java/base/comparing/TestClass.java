package base.comparing;

import base.custom.ByteArraySerializer;
import base.objects.ComplexObject;
import base.objects.SimpleObject;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import org.nustaq.serialization.FSTConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for comparing serializers
 */
public class TestClass {

    /**
     * This class defines the encoders/decoders used during serialization.
     * Usually you just create one global singleton (instantiation of this class is very expensive).
     */
    private static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
    private static Kryo kryo = new Kryo();
    private static ObjectMapper mapper = new ObjectMapper(new SmileFactory());

    public static void main(String[] args) throws IOException {
        SimpleObject simpleObject1 = new SimpleObject(1, "simpleObject1", (byte) 1);
        SimpleObject simpleObject2 = new SimpleObject(2, "simpleObject2", (byte) 2);
        SimpleObject simpleObject3 = new SimpleObject(3, "simpleObject3", (byte) 3);
        List<SimpleObject> simpleObjects = Arrays.asList(simpleObject1, simpleObject2, simpleObject3);

        ComplexObject complexObject1 = new ComplexObject(1, simpleObjects);

        byte[] dataSmile = usingSmile(complexObject1);
        System.out.println(dataSmile);

        byte[] dataFST = usingFST(complexObject1);
        System.out.println(dataFST);

        byte[] dataKryo = usingKryo(complexObject1);
        System.out.println(dataKryo);

        byte[] dataJavaSerial = usingJavaSerialization(complexObject1);
        System.out.println(dataJavaSerial);

    }

    /**
     * Serializing to byte array using https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-smile
     * @param complexObject - object to serialize
     * @return byte array
     * @throws JsonProcessingException
     */
     public static byte[] usingSmile(ComplexObject complexObject) throws JsonProcessingException {
        byte[] smileData = mapper.writeValueAsBytes(complexObject);
        return smileData;
    }

    /**
     * Serializing to byte array using https://mvnrepository.com/artifact/com.esotericsoftware.kryo/kryo
     * @param complexObject - object to serialize
     * @return byte array
     * @throws FileNotFoundException
     */
    public static byte[] usingKryo(ComplexObject complexObject) throws FileNotFoundException {
        kryo.register(complexObject.getClass());
        ByteArrayOutputStream stream = new ByteArrayOutputStream(127);
        Output output = new Output(stream);
        kryo.writeObject(output, complexObject);
        output.flush();
        return stream.toByteArray();
    }

    /**
     * Serializing to byte array using https://mvnrepository.com/artifact/de.ruedigermoeller/fst
     * @param complexObject - object to serialize
     * @return byte array
     */
    public static byte[] usingFST(ComplexObject complexObject) {
        byte[] dataFST = conf.asByteArray(complexObject);
        return dataFST;
    }

    /**
     * Serializing to byte array using custom serialization {@link ByteArraySerializer}
     * @param complexObject - object to serialize
     * @return byte array
     */
    public static byte[] usingJavaSerialization(ComplexObject complexObject) throws IOException {
        ByteArraySerializer bas = new ByteArraySerializer();
        return bas.serialize(complexObject);
    }
}
