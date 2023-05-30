package com.hami.DTO.userDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;

@ApiModel(description = "Contact user")
@Data
public class ContactDto {
    @ApiModelProperty(value = "Name user for contact")
    private String name;
    @Email
    @ApiModelProperty(value = "Email user for contact")
    private String email;
    @ApiModelProperty(value = "Message user in Contact")
    private String message;
}
