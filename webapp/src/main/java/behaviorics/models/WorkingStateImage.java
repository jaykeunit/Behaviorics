package behaviorics.models;


public class WorkingStateImage {
    private int id;
    private byte[] image;
    private int cameraID;

    public WorkingStateImage() {
    }

    public WorkingStateImage(int id, byte[] image, int cameraID) {
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
        return "id: " + id + ", "
                + "image: " + image + ", "
                + "cameraID: " + cameraID;
    }

}
