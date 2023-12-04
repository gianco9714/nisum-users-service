package com.nisum.users.services.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.nisum.users.entities.PhonesEntity;
import com.nisum.users.entities.UserEntity;
import com.nisum.users.services.data.request.CreateUserRequest;
import com.nisum.users.services.data.request.PhoneRequest;
import com.nisum.users.services.data.response.CreateUserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public final class UserMapper {

    public UserEntity mapToUserEntity(CreateUserRequest createUserRequest){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(createUserRequest,userEntity);
        userEntity.setPhones(mapToPhonesEntityList(createUserRequest.getPhones()));
        return userEntity;
    }

    public List<PhonesEntity> mapToPhonesEntityList(List<PhoneRequest> phoneRequests) {
        return phoneRequests.stream()
                .map(this::mapToPhonesEntity)
                .collect(Collectors.toList());
    }

    private PhonesEntity mapToPhonesEntity(PhoneRequest phoneRequest) {
        PhonesEntity phonesEntity = new PhonesEntity();
        phonesEntity.setId(phoneRequest.getId());
        phonesEntity.setNumber(phoneRequest.getNumber());
        phonesEntity.setCitycode(phoneRequest.getCitycode());
        phonesEntity.setCountrycode(phoneRequest.getCountrycode());
        return phonesEntity;
    }

    public CreateUserResponse mapToUserResponse(UserEntity userEntity){
        CreateUserResponse createUserResponse = new CreateUserResponse();
        BeanUtils.copyProperties(userEntity,createUserResponse);

        return createUserResponse;
    }

}
