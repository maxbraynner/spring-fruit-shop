package com.max.fruitshop.api.v1.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VendorDTO {

    @NotNull
    private String name;

    private String vendor_url;

}
