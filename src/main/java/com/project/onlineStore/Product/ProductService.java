package com.project.onlineStore.Product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void createProduct(Product product) {
        validName(product.getProductName());
        productRepository.save(product);

    }

    @Override
    public void updateProduct(Long id, Product product) {
        Product productToUpdate = productRepository.findById(id).orElseThrow(
                ()-> new IllegalStateException(String.format("Product with id: %s doesn't exists.",id)));
        validName(product.getProductName());
        if(product.getProductName() != null)
            productToUpdate.setProductName(product.getProductName());
        if(product.getCategoryName() != null)
            productToUpdate.setCategoryName(product.getCategoryName());
        if(product.getPrice() != 0.0)
            productToUpdate.setPrice(product.getPrice());
        productRepository.save(productToUpdate);
    }

    public void validName(String productName){
        Optional<Product> productOptional = productRepository.getProductByProductName(productName);
        if (productOptional.isPresent()){
            throw new IllegalStateException(String.format("Product name %s already exist",productName));
        }
    }

    @Override
    public void deleteProduct(Long id) {
        boolean productExists = productRepository.existsById(id);
        if(!productExists)
            throw new IllegalStateException(String.format("Product with id: %s doesn't exists.",id));
        productRepository.deleteById(id);
    }
}
