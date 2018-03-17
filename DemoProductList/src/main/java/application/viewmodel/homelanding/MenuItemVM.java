package application.viewmodel.homelanding;

import java.util.List;

/**
 * Created by HoangDuyHao on 1/21/2018.
 */
public class MenuItemVM {
    private String text;
    private String link;

    private List<MenuItemVM> children;

    public MenuItemVM(String text, String link, List<MenuItemVM> children) {
        this.text = text;
        this.link = link;
        this.children = children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<MenuItemVM> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItemVM> children) {
        this.children = children;
    }
}
