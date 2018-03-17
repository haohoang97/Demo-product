package application.data.model;

/**
 * Created by HoangDuyHao on 1/9/2018.
 */
public class ImageProduct {
    private String name;
    private String desc;
    private String image;
    private Menu menu;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String ímage) {
        this.image = ímage;
    }
}
