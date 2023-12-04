package com.nisum.users.services.mapper;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.nisum.users.entities.UserEntity;
import com.nisum.users.services.data.request.CreateUserRequest;
import com.nisum.users.services.data.response.CreateUserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public final class UserMapper {

    public UserEntity mapToUserEntity(CreateUserRequest createUserRequest){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(createUserRequest,userEntity);
        userEntity.setPhones(createUserRequest.getPhones());
        return userEntity;
    }

    public CreateUserResponse mapToUserResponse(UserEntity userEntity){
        CreateUserResponse createUserResponse = new CreateUserResponse();
        BeanUtils.copyProperties(userEntity,createUserResponse);

        return createUserResponse;
    }

}
