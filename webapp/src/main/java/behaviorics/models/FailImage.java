package behaviorics.models;


public class FailImage {

    private int id;
    private byte[] image;
    private int repairID;

    public FailImage() {
    }

    public FailImage(int id, byte[] image, int repairID) {
        this.id = id;
        this.image = image;
        this.repairID = repairID;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id)
    {
        id = _id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] _image)
    {
        image = _image;
    }

    public int getRepairID() {
        return repairID;
    }

    public void setRepairID(int _repairId)
    {
        repairID = _repairId;
    }

    @Override
    public String toString(){
        return "id: " + id + ", "
                + "image: " + image + ", "
                + "repairID: " + repairID;
    }

}
