package com.hami.Service.impl.productImpl;

import com.hami.DTO.productDto.ProductDto;
import com.hami.Entity.product.Product;
import com.hami.Repository.product.ProductRepository;
import com.hami.Service.product.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductImpl implements ProductService {

    @Autowired private ProductRepository productRepository;
    @Autowired private ModelMapper mapper;
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        // Convert DTO to Entity
        Product product = mapToEntity(productDto);
        Product newProduct = productRepository.save(product);

        // Convert Entity to DTO
        ProductDto productResponse = mapToDTO(newProduct);
        return productResponse;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Optional<Product> productFindById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto editProduct(ProductDto productDto, Long productId) {
        // Convert DTO to Entity
        Product product = mapper.map(productDto, Product.class);
        product.setId(productId);
        Product newProduct = productRepository.save(product);

        // Convert Entity to DTO
        ProductDto productResponse = mapToDTO(newProduct);
        return productResponse;
    }

    private ProductDto mapToDTO(Product product) {
        ProductDto productDto = mapper.map(product, ProductDto.class);
        return productDto;
    }

    private Product mapToEntity(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        return product;
    }
}
