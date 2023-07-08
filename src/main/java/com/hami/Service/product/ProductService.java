package com.hami.Service.product;

import com.hami.DTO.productDto.ProductDto;
import com.hami.Entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public ProductDto createProduct(ProductDto productDto);
    public void deleteProduct(Long productId);
    public Optional<Product> productFindById(Long productId);
    public List<Product> findAll();
    public ProductDto editProduct(ProductDto productDto, Long productId);
}
