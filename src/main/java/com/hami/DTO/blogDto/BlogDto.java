package com.hami.DTO.blogDto;

import com.hami.Entity.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel("Blog")
@Data
public class BlogDto {
    @ApiModelProperty(value = "Blog id")
    private Long id;

    @ApiModelProperty(value = "Blog title")
    @NotEmpty
    @Size(min = 2, message = "Blog title should have at least 2 characters")
    private String title;

    @ApiModelProperty(value = "Blog  text")
    @NotEmpty
    @Size(min = 10, message = "Blog text should have at least 10 characters")
    private String text;

    @ApiModelProperty(value = "Blog image")
    @NotEmpty
    private String image;

    @ApiModelProperty(value = "Blog User comments")
    private User user;
}
