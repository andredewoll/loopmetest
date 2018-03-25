package base.objects;

import java.io.Serializable;
import java.util.List;

/**
 * Complex object for various serialization tests
 */
public class ComplexObject implements Serializable {

    private static final long serialVersionUID = 2L;

    /**
     * int value
     */
    private int id;

    /**
     * List of simple base.objects
     */
    private List<SimpleObject> simpleObjects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SimpleObject> getSimpleObjects() {
        return simpleObjects;
    }

    public void setSimpleObjects(List<SimpleObject> simpleObjects) {
        this.simpleObjects = simpleObjects;
    }

    public ComplexObject() {
    }

    public ComplexObject(int id, List<SimpleObject> simpleObjects) {
        this.id = id;
        this.simpleObjects = simpleObjects;
    }

    @Override
    public String toString() {
        return "ComplexObject{" +
                "id=" + id +
                ", simpleObjects=" + simpleObjects +
                '}';
    }
}
