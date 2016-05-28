package behaviorics.failImages;

import java.util.Arrays;

public class FailImagesBO {

    private int id;
    private byte[] image;
    private int repairID;

    public FailImagesBO() {
    }

    public FailImagesBO(int id, byte[] image, int repairID) {
        this.id = id;
        this.image = image;
        this.repairID = repairID;
    }

    public int getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public int getRepairID() {
        return repairID;
    }

    @Override
    public String toString() {
        return "FailImagesBO{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                ", repairID=" + repairID +
                '}';
    }
}
