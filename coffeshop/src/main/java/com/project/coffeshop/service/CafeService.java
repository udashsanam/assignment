package com.project.coffeshop.service;

import com.project.coffeshop.pojo.request.CafePojo;
import com.project.coffeshop.pojo.response.CafeDto;

public interface CafeService extends BaseService{
    CafeDto saveCafe(CafePojo cafePojo);
}
