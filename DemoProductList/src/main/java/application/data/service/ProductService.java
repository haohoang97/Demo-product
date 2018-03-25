package application.data.service;

import application.Application;
import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.repository.ProductRepository;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ManhNguyen on 10/11/17.
 */
@Service
public class ProductService {
    private static final org.apache.log4j.Logger logger = LogManager.getLogger(Application.class);
    @Autowired
    private ProductRepository productRepository;

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void addNewListProducts(List<Product> listProducts) {
        productRepository.save(listProducts);
    }

    public long getTotalProducts() {
        return productRepository.getTotalProducts();
    }

    public PaginableItemList<Product> getListProducts(int pageSize, int pageNumber) {
        PaginableItemList<Product> paginableItemList = null;
        try {
            paginableItemList = new PaginableItemList<>();
            paginableItemList.setPageSize(pageSize);
            paginableItemList.setPageNumber(pageNumber);

            Page<Product> pages = productRepository.findAll(new PageRequest(pageNumber, pageSize));
            paginableItemList.setTotalProducts(pages.getTotalElements());
            paginableItemList.setListData(pages.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paginableItemList;
    }

    public Product findOne(int productId){
        return productRepository.findOne(productId);
    }

    public boolean updateProduct(Product product){
        try{
            productRepository.save(product);
            return true;
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteProduct(int productId){
        try {
            productRepository.delete(productId);
            return true;
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
}
