package com.project.coffeshop.pojo.response;

import com.project.coffeshop.enums.ResponseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalApiResponse {

    private String message;

    private Object data;

    private ResponseStatusEnum status;
}
