package com.hami.Service.product;

import com.hami.DTO.productDto.ProductCategoryDto;
import com.hami.Entity.product.ProductCategory;

import java.util.List;
import java.util.Optional;


public interface ProductCategoryService {
    public void deleteProductCategoryById(Long categoryId);
    public Optional<ProductCategory> findProductCategoryById(Long categoryId);
    public List<ProductCategory> getAllProductCategory();
    public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto);
    public ProductCategoryDto editProductCategory(ProductCategoryDto productCategoryDto, Long productCategoryId);
}
