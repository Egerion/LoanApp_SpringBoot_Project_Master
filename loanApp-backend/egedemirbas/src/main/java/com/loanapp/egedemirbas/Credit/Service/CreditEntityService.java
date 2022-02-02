package com.loanapp.egedemirbas.Credit.Service;

import com.loanapp.egedemirbas.Credit.Dao.CreditDao;
import com.loanapp.egedemirbas.Credit.Entity.Credit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditEntityService {

    private final CreditDao creditDao;

    public List<Credit> findAllCredits(){
        return creditDao.findAllCredits();
    }

    public Credit findAllById(Long id){
        return creditDao.findAllById(id);
    }

    public Credit findByUserIdentityNumber(Long userIdentityNumber){
        return creditDao.findByUserIdentityNumber(userIdentityNumber);
    }

    public Credit findAllByUserIdentityNumberAndUserDateOfBirth(Long userIdentityNumber, Date userDateOfBirth){
        return creditDao.findAllByUserIdentityNumberAndUserDateOfBirth(userIdentityNumber, userDateOfBirth);
    }

    public Credit saveCredit(Credit credit){
        return creditDao.save(credit);
    }
}
