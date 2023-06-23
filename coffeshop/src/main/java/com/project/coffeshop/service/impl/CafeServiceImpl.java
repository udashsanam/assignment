package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.CafeEntity;
import com.project.coffeshop.repo.CafeRepository;
import com.project.coffeshop.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CafeServiceImpl extends BaseServiceImpl implements CafeService{

    @Autowired
    private CafeRepository cafeRepository;

    public CafeServiceImpl(CafeRepository cafeRepository) {
        super(cafeRepository);
        this.cafeRepository = cafeRepository;
    }
}
