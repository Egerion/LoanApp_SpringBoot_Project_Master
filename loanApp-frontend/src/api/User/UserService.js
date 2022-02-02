import axios from "axios";
import React from "react";

class UserService{

    RegisterUser(oData){
                
        const userDto = {
            name: oData.userName,
            surname: oData.userSurname,
            identityNumber: oData.userId,
            phoneNumber: oData.userPhone,
            salary: oData.userSalary,
            age: oData.userAge,
            dateOfBirth: oData.userDateOfBirth,
            guaranteeAmount: oData.userGuaranteeAmount,
            enumGuaranteeType: oData.userGuaranteed,
        }

        const url = "http://localhost:8081/user"
        return axios.post(url, userDto);
    }

}

export default new UserService();