package application.controller.api;

import application.constant.Constant;
import application.data.model.Product;
import application.data.service.ProductService;
import application.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create-product")
    public BaseApiResult createProduct(@RequestBody ProductDataModel product) {
        DataApiResult result = new DataApiResult();

        try {
            if(!"".equals(product.getName()) && !"".equals(product.getShortDesc()) && !"".equals(product.getImage())) {
                ModelMapper modelMapper = new ModelMapper();
                Product productEntity = modelMapper.map(product, Product.class);
                productService.addNewProduct(productEntity);
                result.setSuccess(true);
                result.setMessage("Save product successfully: " + productEntity.getId());
                result.setData(productEntity.getId());
            } else {
                result.setSuccess(false);
                result.setMessage("Invalid model");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
    @GetMapping("/detail/{productId}")
    public BaseApiResult detailProduct(@PathVariable int productId){
        DataApiResult result = new DataApiResult();
        try{
            Product existProduct = productService.findOne(productId);
            if (existProduct == null){
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            }else {
                result.setSuccess(true);
                ModelMapper modelMapper =new ModelMapper();
                ProductDetailModel productDetailModel = new ProductDetailModel();
                result.setData(productDetailModel);
            }
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
