package com.hami.Service.impl.productImpl;

import com.hami.DTO.productDto.ProductCategoryDto;
import com.hami.Entity.product.ProductCategory;
import com.hami.Repository.product.ProductCategoryRepository;
import com.hami.Service.product.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryImpl implements ProductCategoryService {

    @Autowired private ProductCategoryRepository productCategoryRepository;
    @Autowired  private ModelMapper mapper;

    @Override
    public void deleteProductCategoryById(Long categoryId) {
        productCategoryRepository.deleteById(categoryId);
    }

    @Override
    public Optional<ProductCategory> findProductCategoryById(Long categoryId) {
        return productCategoryRepository.findById(categoryId);
    }

    @Override
    public List<ProductCategory> getAllProductCategory() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto) {
        if (productCategoryRepository.existsProductCategoriesByTitle(productCategoryDto.getTitle())) {
            throw new RuntimeException("The product before save");
        }
        ProductCategory productCategory = mapToEntity(productCategoryDto);
        ProductCategory newProductCategory = productCategoryRepository.save(productCategory);

        ProductCategoryDto newProductCategoryDto = mapToDTO(newProductCategory);

        return newProductCategoryDto;
    }

    @Override
    public ProductCategoryDto editProductCategory(ProductCategoryDto productCategoryDto, Long productCategoryId) {
        ProductCategory productCategory = mapToEntity(productCategoryDto);
        productCategory.setId(productCategoryId);
        ProductCategory editProductCategory = productCategoryRepository.save(productCategory);

        ProductCategoryDto newProductCategoryDto = mapToDTO(editProductCategory);
        return newProductCategoryDto;
    }

    private ProductCategoryDto mapToDTO(ProductCategory productCategory) {
        ProductCategoryDto productCategoryDto = mapper.map(productCategory, ProductCategoryDto.class);
        return productCategoryDto;
    }

    private ProductCategory mapToEntity(ProductCategoryDto productCategoryDto) {
        ProductCategory productCategory = mapper.map(productCategoryDto, ProductCategory.class);
        return productCategory;
    }
}
