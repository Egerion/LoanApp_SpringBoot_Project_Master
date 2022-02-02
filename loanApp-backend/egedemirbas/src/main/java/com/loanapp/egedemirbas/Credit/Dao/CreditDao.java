package com.loanapp.egedemirbas.Credit.Dao;

import com.loanapp.egedemirbas.Credit.Entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CreditDao extends JpaRepository<Credit, Long> {

    @Query("select credit from Credit credit")
    List<Credit> findAllCredits();

    Credit findAllById(Long id);

    Credit findByUserIdentityNumber(Long userIdentityNumber);

    @Query("select credit from Credit credit where credit.userIdentityNumber = :userIdentityNumber and credit.userDateOfBirth =:userDateOfBirth")
    Credit findAllByUserIdentityNumberAndUserDateOfBirth(@Param("userIdentityNumber") Long userIdentityNumber, @Param("userDateOfBirth")Date userDateOfBirth);
}
