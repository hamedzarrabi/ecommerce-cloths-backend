package com.hami.DTO.productDto;

import com.hami.Entity.product.ProductCategory;
import com.hami.Entity.product.ProductDiscount;
import com.hami.Entity.product.ProductInventory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Product")
@Data
public class ProductDto {
    @ApiModelProperty(value = "product id")
    private Long id;
    @ApiModelProperty(value = "title product")
    private String title;
    @ApiModelProperty(value = "price product")
    private double price;
    @ApiModelProperty(value = "image for eny product")
    private String image;
    @ApiModelProperty(value = "keyword for product")
    private String keyWord;
    @ApiModelProperty(value = "discount id for product")
    private Long discountId;
    @ApiModelProperty(value = "category id for product")
    private Long categoryId;
    @ApiModelProperty(value = "inventory id for product")
    private Long inventoryId;
}
