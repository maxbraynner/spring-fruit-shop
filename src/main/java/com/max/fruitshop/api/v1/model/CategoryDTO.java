package com.max.fruitshop.api.v1.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDTO {

    private Long id;

    @NotNull
    private String name;

}
