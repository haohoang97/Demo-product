package application.model;

public class ProductDetailModel {
    private int id;
    private String name;
    private String image;
    private String shotDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShotDesc() {
        return shotDesc;
    }

    public void setShotDesc(String shotDesc) {
        this.shotDesc = shotDesc;
    }
}