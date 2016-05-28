package behaviorics.workingStateImages;

import java.util.Arrays;

public class WorkingStateImagesBO {

    private int id;
    private byte[] image;
    private int cameraID;

    public WorkingStateImagesBO() {
    }

    public WorkingStateImagesBO(int id, byte[] image, int cameraID) {
        this.id = id;
        this.image = image;
        this.cameraID = cameraID;
    }

    public int getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public int getCameraID() {
        return cameraID;
    }

    @Override
    public String toString() {
        return "WorkingStateImagesBO{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                ", cameraID=" + cameraID +
                '}';
    }
}
