package com.project.coffeshop.service.impl;

import com.project.coffeshop.entity.CafeEntity;
import com.project.coffeshop.entity.UserEntity;
import com.project.coffeshop.enums.Role;
import com.project.coffeshop.exception.UnAuthorizeException;
import com.project.coffeshop.pojo.request.CafePojo;
import com.project.coffeshop.pojo.response.CafeDto;
import com.project.coffeshop.repo.CafeRepository;
import com.project.coffeshop.repo.UserRepository;
import com.project.coffeshop.repo.UserRoleRepository;
import com.project.coffeshop.service.CafeService;
import com.project.coffeshop.service.UserTokenService;
import com.project.coffeshop.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CafeServiceImpl extends BaseServiceImpl<CafeEntity, Long> implements CafeService{

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository  userRoleRepository;

    private final UserTokenService  userTokenService;

    public CafeServiceImpl(CafeRepository cafeRepository, UserTokenService userTokenService) {
        super(cafeRepository);
        this.cafeRepository = cafeRepository;
        this.userTokenService = userTokenService;
    }

    @Override
    public CafeDto saveCafe(CafePojo cafePojo, String token) {

        // cafe are can only be crated by super admin
        UserEntity user = userRepository.findByUsername(cafePojo.getUsername());
        String role = userTokenService.getClaims(token).get(Constants.USER_ROLES_CLAIM).toString();
        if(!role.contains(Role.SUPER_ADMIN.toString())) throw new UnAuthorizeException("You are not allowed to create cafe");
        if(user ==null) throw  new RuntimeException("User not fund");
        List<String> roles = userRoleRepository.findAllRolesByUserId(user.getId());
        if(!roles.contains(Role.SUPER_ADMIN.toString())) throw new UnAuthorizeException(" You are not allowed to crate cafe");


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

    @Override
    public List<CafeDto> findAllCafe() {
        List<CafeEntity> cafeEntities = cafeRepository.findAll();
        List<CafeDto> cafeDtos = cafeEntities.parallelStream()
                .map(cafeEntity -> new CafeDto(cafeEntity.getId(), cafeEntity.getName(), cafeEntity.getAddress()))
                .collect(Collectors.toList());
        return  cafeDtos;
    }
}
