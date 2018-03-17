package application.controller.web;

import application.data.model.ImageProduct;
import application.data.model.Menu;
import application.data.model.Product;
import application.data.model.Student;
import application.data.service.ProductService;
import application.viewmodel.homelanding.HomeLandingVM;
import application.viewmodel.common.LogoVM;
import application.viewmodel.homelanding.MenuItemVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by ManhNguyen on 10/11/17.
 */

@Controller
@RequestMapping(path="/")
public class HomeController {

    @Autowired
    private ProductService productService;

    private String[] images = {
        "https://images-na.ssl-images-amazon.com/images/I/519rVW4jTIL._SL500_SS135_.jpg",
        "https://images-na.ssl-images-amazon.com/images/I/51JJ0e-i2yL._SL500_SS135_.jpg",
        "https://images-na.ssl-images-amazon.com/images/I/517Q0wwZUXL._SL500_SS135_.jpg",
        "https://images-na.ssl-images-amazon.com/images/I/41odx4vtkyL._SL500_SS135_.jpg",
        "https://images-na.ssl-images-amazon.com/images/I/41KCisxFTwL._SL500_SS135_.jpg"
    };

    @GetMapping(path="admin")
    public String admin(Model model) {

        long totalProducts = productService.getTotalProducts();
        if(totalProducts <= 0) {
            ArrayList<Product> listProducts= new ArrayList<>();
            Random random = new Random();

            for(int i = 1; i <= 100; ++i) {
                Product p = new Product();
                p.setCreatedDate(new Date());
                p.setName("Product " + i);
                p.setShortDesc("Description for product " + i);
                p.setImage(images[random.nextInt(images.length)]);
                listProducts.add(p);
            }

            productService.addNewListProducts(listProducts);
            model.addAttribute("message", "Total added products: " + listProducts.size());
        } else {
            model.addAttribute("message", "Total existed products: " + totalProducts);
        }

        return "admin";
    }

    @GetMapping(path="/")
    public String index(Model model,
                        @RequestParam(value="pageSize", required=false) String ps,
                        @RequestParam(value="pageNumber", required=false) String pn){

        try {
            int pageSize = Integer.parseInt(ps);
            int pageNumber = Integer.parseInt(pn);
            if(pageSize > 0 && pageNumber >= 0) {
                model.addAttribute("paginableItem", productService.getListProducts(pageSize, pageNumber));
            } else {
                model.addAttribute("paginableItem", productService.getListProducts(10, 0));
            }
        } catch (Exception ex) {
            model.addAttribute("paginableItem", productService.getListProducts(10, 0));
        }

        return "index";
    }

    @GetMapping(path = "/hello")
    public String hello(Model model){
        model.addAttribute("message", "Wellcome to thymeleaf");
        ArrayList<Student> listStudents = new ArrayList<>();
        listStudents.add(new Student("nam", 69));
        listStudents.add(new Student("manh", 55));
        model.addAttribute("listStudents", listStudents);

        return "hello";
    }


    @GetMapping(path = "list_image")
    public String image(Model model){
        ArrayList<ImageProduct> listImages = new ArrayList<>();
        ArrayList<Menu> menus = new ArrayList<>();
        Random random = new Random();
        ArrayList<String> img = new ArrayList<>();
        ArrayList<String> menuList1 = new ArrayList<>();
        menuList1.add("Home");
        menuList1.add("Admin");
        menuList1.add("Image");
        menuList1.add("Name");
        menuList1.add("Desc");
        for (int i = 0; i< 5; ++i){
            Menu m = new Menu();
            m.setName(menuList1.get(i));
            menus.add(m);
        }
        img.add("http://genknews.genkcdn.vn/2017/photo-7-1498812558056-1499937607553.jpg");
        img.add("https://vignette.wikia.nocookie.net/onepiece/images/7/7b/Wake_up%21.png/revision/latest?cb=20140916082002");
        img.add("http://static.hdonline.vn/i/resources/new/video/2016/07/07/top-10-nhan-vat-an-tuong-va-manh-nhat-one-piece.jpg");
        img.add("https://otakukart.com/animeblog/wp-content/uploads/2017/10/ynclxch3hp6svpu2sv1z.jpg");
        img.add("https://nerdist.com/wp-content/uploads/2017/07/one-piece-cover-970x545.jpg");

        for(int i = 1; i <= 8; ++i) {
            ImageProduct p = new ImageProduct();
            p.setName("Image " + i);
            p.setDesc("Description for product " + i);
            p.setImage(img.get(random.nextInt(img.size())));
            p.setMenu(menus.get(random.nextInt(menus.size())));
            listImages.add(p);

        }
        model.addAttribute("listImages", listImages);
        return "list_image";
    }

    @GetMapping(path = "/homelanding")
    public String homeLanding(Model model){
        HomeLandingVM vm = new HomeLandingVM();
        LogoVM logo = new LogoVM("SpringMVC", "http://arena.fpt.edu.vn/wp-content/uploads/2010/11/wwf-logo-design.jpg", "SpringMVC", "SMVC");
        vm.setLogo(logo);
        MenuItemVM menuChild1 = new MenuItemVM("MVC1", "https://www.google.com.vn/?hl=vi", null );
        MenuItemVM menuChild2 = new MenuItemVM("MVC2", "https://www.google.com.vn/?hl=vi", null );
        MenuItemVM menuChild3 = new MenuItemVM("MVC3", "https://www.google.com.vn/?hl=vi", null );
        MenuItemVM menuChild4 = new MenuItemVM("MVC4", "https://www.google.com.vn/?hl=vi", null );
        MenuItemVM menuChild5 = new MenuItemVM("MVC5", "https://www.google.com.vn/?hl=vi", null );
        ArrayList<MenuItemVM> listMenu = new ArrayList<>();
        listMenu.add(menuChild1);
        listMenu.add(menuChild2);
        listMenu.add(menuChild3);
        listMenu.add(menuChild4);
        listMenu.add(menuChild5);
        MenuItemVM menu = new MenuItemVM("MVC", "https://www.google.com.vn/?hl=vi", listMenu);
        vm.setMenu(menu);
        ArrayList<ImageProduct> listImages = new ArrayList<>();
        Random random = new Random();
        ArrayList<String> img = new ArrayList<>();
        img.add("http://arena.fpt.edu.vn/wp-content/uploads/2010/11/wwf-logo-design.jpg");
        img.add("http://arena.fpt.edu.vn/wp-content/uploads/2010/11/wwf-logo-design.jpg");
        img.add("http://arena.fpt.edu.vn/wp-content/uploads/2010/11/wwf-logo-design.jpg");
        img.add("http://arena.fpt.edu.vn/wp-content/uploads/2010/11/wwf-logo-design.jpg");
        img.add("http://arena.fpt.edu.vn/wp-content/uploads/2010/11/wwf-logo-design.jpg");
        for(int i = 1; i <= 8; ++i) {
            ImageProduct p = new ImageProduct();
            p.setName("Image " + i);
            p.setDesc("Description for product " + i);
            p.setImage(img.get(random.nextInt(img.size())));
            listImages.add(p);
        }
        vm.setImg(listImages);
        model.addAttribute("vm", vm);
        return "homelanding";
    }
}
