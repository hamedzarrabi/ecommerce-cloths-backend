package com.hami.Controller.productController;

import com.hami.DTO.productDto.ProductDto;
import com.hami.Entity.product.Product;
import com.hami.Service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Api(value = "CRUD Product API for Product Resource")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Create Product REST API")
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDto> createProduct(
            @RequestParam("title") String title,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile image,
            @RequestParam("keyword") String keyword,
            @RequestParam("category_id") Long categoryId,
            @RequestParam("inventory_id") Long inventoryId,
            @RequestParam("discount_id") Long discountId
    ) {
        ProductDto productDto = new ProductDto();

        String imageAddress = "images/products/" + productDto.getTitle();
        String originalAddressImage = "D:/Backend/E-commenrce-cloths-full-website/ecommerce-cloths-frontend/public/images/products/" + productDto.getTitle();

        insertProduct(title, price, image, keyword, categoryId, inventoryId, discountId, productDto, imageAddress, originalAddressImage);

        ProductDto newProductDto = productService.createProduct(productDto);

        return new ResponseEntity<ProductDto>(newProductDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Edit Product REST API")
    @PutMapping("/edit/{productId}")
    public ResponseEntity<?> editProduct(
            @RequestParam("title") String title,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile image,
            @RequestParam("keyword") String keyword,
            @RequestParam("category_id") Long categoryId,
            @RequestParam("inventory_id") Long inventoryId,
            @RequestParam("discount_id") Long discountId,
            @PathVariable("productId") Long productId
    ) {
        Optional<Product> product = productService.productFindById(productId);
        ProductDto productDto = new ProductDto();

        System.out.println("product id: " + product.get().getId());

        String imageAddress = "images/products/" + productDto.getTitle();
        String originalAddressImage = "D:/Backend/E-commenrce-cloths-full-website/ecommerce-cloths-frontend/public/images/products/" + productDto.getTitle();

        if (product.get().getId() != null) {
            insertProduct(title, price, image, keyword, categoryId, inventoryId, discountId, productDto, imageAddress, originalAddressImage);

            ProductDto newProductDto = productService.editProduct(productDto, product.get().getId());

            return new ResponseEntity<ProductDto>(newProductDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("productId not found", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "delete product REST API")
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<String>("Deleted product id:" + productId + " Successfully.", HttpStatus.OK);
    }

    @ApiOperation(value = "Find product By productId REST API")
    @GetMapping("/findProduct/{productId}")
    public ResponseEntity<Optional<Product>> findProduct(@PathVariable("productId") Long productId) {
        Optional<Product> product = productService.productFindById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @ApiOperation(value = "find all products REST API")
    @GetMapping("/finaAllProducts")
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> product = productService.findAll();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    // For insert product for create and edit product
    private void insertProduct(
            @RequestParam("title") String title,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile image,
            @RequestParam("keyword") String keyword,
            @RequestParam("category_id") Long categoryId,
            @RequestParam("inventory_id") Long inventoryId,
            @RequestParam("discount_id") Long discountId,
            ProductDto productDto,
            String imageAddress,
            String originalAddressImage) {

        productDto.setTitle(title);
        productDto.setPrice(price);
        productDto.setImage(imageAddress + "/" + image.getOriginalFilename());
        productDto.setKeyWord(keyword);
        productDto.setCategoryId(categoryId);
        productDto.setInventoryId(inventoryId);
        productDto.setDiscountId(discountId);

        savePicture(image, originalAddressImage);
    }

    private void savePicture(MultipartFile image, String address) {
        saveImage(image, address);
    }

    public static void saveImage(MultipartFile image, String address) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        try {
            Path path = Paths.get(address);
            Files.createDirectories(path);
            Files.copy(image.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

}
