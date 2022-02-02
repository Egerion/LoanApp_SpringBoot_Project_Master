package com.loanapp.egedemirbas.Credit.Controller;

import com.loanapp.egedemirbas.Credit.Entity.Credit;
import com.loanapp.egedemirbas.Credit.Enum.EnumCreditResult;
import com.loanapp.egedemirbas.Credit.Exception.CreditNotFoundException;
import com.loanapp.egedemirbas.Credit.Service.CreditEntityService;
import com.loanapp.egedemirbas.Credit.Service.CreditModelService;
import com.loanapp.egedemirbas.User.Converter.UserConverter;
import com.loanapp.egedemirbas.User.Dto.UserDto;
import com.loanapp.egedemirbas.User.Entity.User;
import com.loanapp.egedemirbas.User.Exception.UserNotFoundException;
import com.loanapp.egedemirbas.User.Service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/credit")
@CrossOrigin
@RequiredArgsConstructor
public class CreditController {

    private final CreditEntityService creditEntityService;
    private final CreditModelService creditModelService;
    private final UserEntityService userEntityService;

    @GetMapping("/list")
    public List<Credit> findALlCredits(){
        return creditEntityService.findAllCredits();
    }

    @GetMapping("/{userIdentityNumber}/{userDateOfBirth}")
    public Credit findAllByUserIdentityNumberAndUserDateOfBirth(@PathVariable Long userIdentityNumber, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date userDateOfBirth){

        Credit credit = creditEntityService.findAllByUserIdentityNumberAndUserDateOfBirth(userIdentityNumber, userDateOfBirth);
        if(credit == null){
            User user = userEntityService.findUserByIdentityNumber(userIdentityNumber);
            if(user != null){
                throw new CreditNotFoundException("Please check your account details.");
            }
            else{
                throw new UserNotFoundException("User cannot be found in database!");
            }
        }
        return credit;
    }

    @PostMapping("")
    public EnumCreditResult setUserCredit(@RequestBody UserDto userDto){

        Credit credit;
        User user = UserConverter.INSTANCE.convertUserDtoToUser(userDto);
        if(user != null){
            credit =  creditEntityService.findAllByUserIdentityNumberAndUserDateOfBirth(userDto.getIdentityNumber(), userDto.getDateOfBirth());
            if(credit == null){
                credit = creditModelService.CreateUserCredit(user);
            }
        }
        else{
            throw new UserNotFoundException("user not found!");
        }
        return credit.getCreditResult();
    }
}