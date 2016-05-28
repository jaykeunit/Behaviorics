package behaviorics.floorPlan;

import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;

public class FloorPlanBO {
    private int id;
    private byte[] image;
    private int floorId;

    public FloorPlanBO(){}

    public FloorPlanBO(int _id, byte[] _image, int _floorId) {
        id = _id;
        image = _image;
        floorId = _floorId;
    }

    public FloorPlanBO(int _id, String _image, int _floorId) {
        id = _id;
        image = DatatypeConverter.parseBase64Binary(_image);
        floorId = _floorId;
    }


    public int getId() {return id;}

    public byte[] getImage() {return image;}

    public int getFloorId() {return floorId;}


    @Override
    public String toString() {
        return "id: " + id + ", "
                + "image: " + Arrays.toString(image) + ", "
                + "floorId: " + floorId;
    }
}
