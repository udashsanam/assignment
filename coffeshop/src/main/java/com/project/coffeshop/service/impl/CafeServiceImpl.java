package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.CafeEntity;
import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.pojo.request.CafePojo;
import com.project.coffeshop.pojo.response.CafeDto;
import com.project.coffeshop.repo.CafeRepository;
import com.project.coffeshop.repo.UserRepository;
import com.project.coffeshop.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CafeServiceImpl extends BaseServiceImpl<CafeEntity, Long> implements CafeService{

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private UserRepository userRepository;

    public CafeServiceImpl(CafeRepository cafeRepository) {
        super(cafeRepository);
        this.cafeRepository = cafeRepository;
    }

    @Override
    public CafeDto saveCafe(CafePojo cafePojo) {

        UserEntity user = userRepository.findByUsername(cafePojo.getUsername());
        if(user ==null) throw  new RuntimeException("User not fund");
        CafeEntity cafe = new CafeEntity();
        cafe.setName(cafePojo.getName());
        cafe.setAddress(cafePojo.getAddress());

        try{
            cafe = cafeRepository.save(cafe);
        }catch (Exception ex){
            throw new RuntimeException("Error saving cafe");
        }
        return new CafeDto(cafe.getId(), cafe.getName(), cafe.getAddress());
    }
}
