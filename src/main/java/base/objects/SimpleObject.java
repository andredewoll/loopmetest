package base.objects;

import java.io.Serializable;

/**
 * Simple object for various serialization tests
 */
public class SimpleObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * int value
     */
    private int intField;
    /**
     * String value
     */
    private String stringField;
    /**
     * byte value
     */
    private byte byteField;

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public byte getByteField() {
        return byteField;
    }

    public void setByteField(byte byteField) {
        this.byteField = byteField;
    }

    public SimpleObject() {
    }

    public SimpleObject(int intField, String stringField, byte byteField) {
        this.intField = intField;
        this.stringField = stringField;
        this.byteField = byteField;
    }

    @Override
    public String toString() {
        return "SimpleObject{" +
                "intField=" + intField +
                ", stringField='" + stringField + '\'' +
                ", byteField=" + byteField +
                '}';
    }
}
