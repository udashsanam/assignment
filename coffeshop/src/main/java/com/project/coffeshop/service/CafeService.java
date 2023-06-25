package com.project.coffeshop.service;

import com.project.coffeshop.entity.CafeEntity;
import com.project.coffeshop.pojo.request.CafePojo;
import com.project.coffeshop.pojo.response.CafeDto;

import java.util.List;

public interface CafeService extends BaseService<CafeEntity, Long> {
    CafeDto saveCafe(CafePojo cafePojo, String token);

    List<CafeDto> findAllCafe();
}
