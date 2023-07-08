package com.hami.DTO.productDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Category")
@Data
public class ProductCategoryDto {
    @ApiModelProperty("Category Id")
    private Long id;
    @ApiModelProperty("Category Title")
    private String title;
    @ApiModelProperty("Category description")
    private String description;
}
