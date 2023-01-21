package com.lazybeast.springSecurity.service;

import com.lazybeast.springSecurity.dto.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    List<Product> productList = new ArrayList<Product>(){{
        add(new Product(1, "prod1", 1, 20));
        add(new Product(2, "prod2", 2, 30));
        add(new Product(3, "prod3", 3, 40));
        add(new Product(4, "prod4", 4, 50));
        add(new Product(5, "prod5", 5, 60));
        add(new Product(6, "prod6", 6, 70));
        add(new Product(7, "prod7", 7, 80));
    }};



    public List<Product> getProducts() {
        return productList;
    }

    public Product getProduct(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
    }
}
