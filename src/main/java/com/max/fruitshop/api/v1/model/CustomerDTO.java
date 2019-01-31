package com.max.fruitshop.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;

}
