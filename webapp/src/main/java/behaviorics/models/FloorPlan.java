package behaviorics.models;

import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;

public class FloorPlan {
    private int id;
    private byte[] image;
    private int floorId;

    public FloorPlan(){}

    public FloorPlan(int _id, byte[] _image, int _floorId) {
        id = _id;
        image = _image;
        floorId = _floorId;
    }

    public int getId() {return id;}

    public byte[] getImage() {return image;}

    public void setImage(byte[] _image){image = _image;}

    public int getFloorId() {return floorId;}

    public void setFloorId(int _floorId){floorId = _floorId;}


    @Override
    public String toString() {
        return "id: " + id + ", "
                + "image: " + Arrays.toString(image) + ", "
                + "floorId: " + floorId;
    }
}
