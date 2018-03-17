package application.viewmodel.homelanding;

import application.data.model.ImageProduct;
import application.viewmodel.common.LogoVM;

import java.util.ArrayList;

/**
 * Created by HoangDuyHao on 1/21/2018.
 */
public class HomeLandingVM {
    private LogoVM logo;
    private MenuItemVM menu;
    private ArrayList<ImageProduct> img;

    public ArrayList<ImageProduct> getImg() {
        return img;
    }

    public void setImg(ArrayList<ImageProduct> img) {
        this.img = img;
    }

    public MenuItemVM getMenu() {
        return menu;
    }

    public void setMenu(MenuItemVM menu) {
        this.menu = menu;
    }

    public LogoVM getLogo() {
        return logo;
    }

    public void setLogo(LogoVM logo) {
        this.logo = logo;
    }
}
