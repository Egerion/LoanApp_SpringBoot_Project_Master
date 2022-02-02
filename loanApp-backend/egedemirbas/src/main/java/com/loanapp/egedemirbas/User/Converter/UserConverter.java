package com.loanapp.egedemirbas.User.Converter;

import com.loanapp.egedemirbas.User.Dto.UserDto;
import com.loanapp.egedemirbas.User.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    User convertUserDtoToUser(UserDto userdto);

    User updateUserFromDto(UserDto userDto, @MappingTarget User user);
}