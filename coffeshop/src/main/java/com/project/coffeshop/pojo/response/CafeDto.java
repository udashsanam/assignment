package com.project.coffeshop.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CafeDto {

    private Long id;

    private String name;

    private String address;

    public CafeDto(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
