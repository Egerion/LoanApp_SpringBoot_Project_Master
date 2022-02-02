package com.loanapp.egedemirbas.User.Controller;

import com.loanapp.egedemirbas.User.Converter.UserConverter;
import com.loanapp.egedemirbas.User.Dto.UserDto;
import com.loanapp.egedemirbas.User.Entity.User;
import com.loanapp.egedemirbas.User.Exception.UserNotFoundException;
import com.loanapp.egedemirbas.User.Service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserEntityService userEntityService;

    @GetMapping("/list")
    public List<User> findAllUsers(){
        return userEntityService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id){
        User user = userEntityService.findUserById(id);
        if(user == null){
            throw new UserNotFoundException("user not found with id: " + id);
        }
        return user;
    }

    @PostMapping("/response")
    public ResponseEntity<Object> saveFromDto(@RequestBody UserDto userDto){

            User user = UserConverter.INSTANCE.convertUserDtoToUser(userDto);
            user = userEntityService.saveUser(user);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("")
    public UserDto saveNewUserFromDto(@RequestBody UserDto userDto) throws HttpResponseException {

        User user = userEntityService.findUserByIdentityNumber(userDto.getIdentityNumber());
        if(user != null){
            throw new HttpResponseException(HttpStatus.SC_NOT_ACCEPTABLE,"User already exist in system and applied to credit. Please use /credit/query to check your credit result.");
        }
        else{
            user = UserConverter.INSTANCE.convertUserDtoToUser(userDto);
            user = userEntityService.saveUser(user);
        }
        return userDto;
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody UserDto userDto) {
        User user = userEntityService.findUserById(userDto.getId());
        if(user != null){
            User updatedUser = UserConverter.INSTANCE.updateUserFromDto(userDto, user);
            userEntityService.saveUser(updatedUser);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        User user = userEntityService.findUserById(id);
        if(user != null)
            userEntityService.deleteUserById(user);
    }
}