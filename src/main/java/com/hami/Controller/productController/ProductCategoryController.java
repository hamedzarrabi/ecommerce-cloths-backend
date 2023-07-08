package com.hami.Controller.productController;

import com.hami.DTO.productDto.ProductCategoryDto;
import com.hami.Entity.product.ProductCategory;
import com.hami.Service.product.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "CRUD Rest APIS for Category resource")
@RestController
@RequestMapping("/category")
public class ProductCategoryController {

    @Autowired private ProductCategoryService productCategoryService;

    @ApiOperation(value = "create category")
    @PostMapping("/create")
    public ResponseEntity<ProductCategoryDto> createProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        ProductCategoryDto newProductCategoryDto = productCategoryService.createProductCategory(productCategoryDto);
        return new ResponseEntity<>(newProductCategoryDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "edit category")
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editProductCategory(@RequestBody ProductCategoryDto productCategoryDto, @PathVariable("id") Long productCategoryId) {
        Optional<ProductCategory> categoryId = productCategoryService.findProductCategoryById(productCategoryId);

        ProductCategoryDto newProductCategoryDto = new ProductCategoryDto();
        if (categoryId.get().getId() != null) {
            newProductCategoryDto.setTitle(productCategoryDto.getTitle());
            newProductCategoryDto.setDescription(productCategoryDto.getDescription());

            ProductCategoryDto productCategoryDtoNew = productCategoryService.editProductCategory(newProductCategoryDto, categoryId.get().getId());

            return new ResponseEntity<>(productCategoryDtoNew, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("productCategoryId not found", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "delete category")
    @DeleteMapping("/deleteProductCategory/{id}")
    public ResponseEntity<String> deleteProductCategoryById(@PathVariable("id") Long productCategoryId) {
        productCategoryService.deleteProductCategoryById(productCategoryId);
        return new ResponseEntity<>("Deleted productCategory id:" + productCategoryId + " Successfully.", HttpStatus.OK);
    }


    @ApiOperation(value = "find all category")
    @GetMapping("/findAll")
    public ResponseEntity<List<ProductCategory>> getAllProductCategory() {
        List<ProductCategory> allProductCategory = productCategoryService.getAllProductCategory();
        return new ResponseEntity<>(allProductCategory, HttpStatus.OK);
    }
    @ApiOperation(value = "find categoryById")
    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<ProductCategory>> findProductCategoryById(@PathVariable("id") Long productCategoryId) {
        Optional<ProductCategory> findProductCategoryById = productCategoryService.findProductCategoryById(productCategoryId);
        return new ResponseEntity<>(findProductCategoryById, HttpStatus.OK);
    }
}
