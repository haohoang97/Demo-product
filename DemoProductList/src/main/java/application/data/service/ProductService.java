package application.data.service;

import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ManhNguyen on 10/11/17.
 */
@Service
public class ProductService {

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
        PaginableItemList<Product> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<Product> pages = productRepository.findAll(new PageRequest(pageNumber, pageSize));
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }

    public Product findOne(int productId){
        productRepository.getOne(productId);
        return productRepository.getOne(productId);
    }

}
