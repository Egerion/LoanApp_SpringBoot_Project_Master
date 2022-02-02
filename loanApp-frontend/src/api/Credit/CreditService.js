import axios from "axios";

class CreditService{

    CreditQueryResult(userId, DateOfBirth){

        const url = 'http://localhost:8081/credit/' + userId + '/' + DateOfBirth;
        return axios.get(url);
    }

    CreditCreateNew(oData){

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

        const url = "http://localhost:8081/credit"
        return axios.post(url, userDto);
    }
}

export default new CreditService();