package com.project.onlineStore.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();
    void createProduct(Product product);
    void updateProduct(Long id,Product product);
    void deleteProduct(Long id);

}
