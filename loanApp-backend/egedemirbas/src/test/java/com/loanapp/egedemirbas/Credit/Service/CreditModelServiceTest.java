package com.loanapp.egedemirbas.Credit.Service;

import com.loanapp.egedemirbas.Credit.Entity.Credit;
import com.loanapp.egedemirbas.Credit.Enum.EnumCreditResult;
import com.loanapp.egedemirbas.User.Entity.User;
import com.loanapp.egedemirbas.User.Enum.EnumGuaranteeType;
import com.loanapp.egedemirbas.User.Service.UserEntityService;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreditModelServiceTest extends TestCase {

    @BeforeClass
    private User CreateDummyUserBody(){
        User user = new User();
        user.setDateOfBirth(new Date());
        user.setName("John");
        user.setSurname("Doe");
        return user;
    }

    @Before
    private Long CalculateUserCredit(User user){
        long ageIndex;
        long guaranteeIndex = 0L;
        long creditScore;
        float salaryIndex = user.getSalary();

        if(user.getAge() >= 18 && user.getAge() < 25)
            ageIndex = 22L;
        else if(user.getAge() >= 25 && user.getAge() < 35)
            ageIndex = 25L;
        else
            ageIndex = 35L;
        if(user.getEnumGuaranteeType() == EnumGuaranteeType.GUARANTEED)
            guaranteeIndex = 100L;
        creditScore = Math.round(1.354 * ageIndex + 0.085 * salaryIndex + 2.12 * guaranteeIndex);
        return creditScore;
    }

    @After
    public Credit CreateUserCredit(User user){

        float totalCash;
        float guaranteeIndex = 0;
        float userCashLimit = 0;
        float userSalary = user.getSalary();

        int creditLimitMulti = 4;

        EnumCreditResult enumCreditResult = null;

        Long userCredit = this.CalculateUserCredit(user);

        if(userCredit < 500L){
            enumCreditResult = EnumCreditResult.REJECTED;
        }
        else if(userCredit  >= 500 && userCredit <= 1000){
            if(userSalary < 5000){
                userCashLimit = 10000;
                guaranteeIndex = (float) 0.10;
            }
            else if(userSalary >= 5000 && userSalary <= 10000){
                userCashLimit = 20000;
                guaranteeIndex = (float) 0.20;
            }
            else if( userSalary > 10000){
                userCashLimit = userSalary * creditLimitMulti / 2;
                guaranteeIndex = (float) 0.25;
            }
            enumCreditResult = EnumCreditResult.CONFIRMED;
        }
        else if(userSalary > 10000){
            userCashLimit = userSalary * creditLimitMulti;
            guaranteeIndex = (float) 0.50;
            enumCreditResult = EnumCreditResult.CONFIRMED;
        }

        if(user.getEnumGuaranteeType() == EnumGuaranteeType.GUARANTEED)
            totalCash = userCashLimit + user.getGuaranteeAmount() * guaranteeIndex;
        else
            totalCash = userCashLimit;

        Credit credit = new Credit();
        credit.setInsDate(new Date());
        credit.setCreditResult(enumCreditResult);
        credit.setUserDateOfBirth(user.getDateOfBirth());
        credit.setUserIdentityNumber(user.getIdentityNumber());
        credit.setCreditAmount(totalCash);
        return credit;
    }

    //overloaded method to avoid credit score calculation and passing desired custom score.
    @After
    public Credit CreateUserCredit(User user, Long userCredit){

        float totalCash;
        float guaranteeIndex = 0;
        float userCashLimit = 0;
        float userSalary = user.getSalary();

        int creditLimitMulti = 4;

        EnumCreditResult enumCreditResult = null;

        if(userCredit < 500L){
            enumCreditResult = EnumCreditResult.REJECTED;
        }
        else if(userCredit  >= 500 && userCredit <= 1000){
            if(userSalary < 5000){
                userCashLimit = 10000;
                guaranteeIndex = (float) 0.10;
            }
            else if(userSalary >= 5000 && userSalary <= 10000){
                userCashLimit = 20000;
                guaranteeIndex = (float) 0.20;
            }
            else if( userSalary > 10000){
                userCashLimit = userSalary * creditLimitMulti / 2;
                guaranteeIndex = (float) 0.25;
            }
            enumCreditResult = EnumCreditResult.CONFIRMED;
        }
        else if(userSalary > 10000){
            userCashLimit = userSalary * creditLimitMulti;
            guaranteeIndex = (float) 0.50;
            enumCreditResult = EnumCreditResult.CONFIRMED;
        }

        if(user.getEnumGuaranteeType() == EnumGuaranteeType.GUARANTEED)
            totalCash = userCashLimit + user.getGuaranteeAmount() * guaranteeIndex;
        else
            totalCash = userCashLimit;

        Credit credit = new Credit();
        credit.setInsDate(new Date());
        credit.setCreditResult(enumCreditResult);
        credit.setUserDateOfBirth(user.getDateOfBirth());
        credit.setUserIdentityNumber(user.getIdentityNumber());
        credit.setCreditAmount(totalCash);
        return credit;
    }

    //Kredi skoru 500’ün altında ise kullanıcı reddedilir. (Kredi sonucu: Red)
    @Test
    void shouldReturnRejectedCreditScore499(){

        User user = CreateDummyUserBody();
        user.setSalary(5000);
        Credit credit = CreateUserCredit(user, 499L);
        EnumCreditResult creditResult = credit.getCreditResult();

        System.out.print("credit result: " + creditResult.toString() + "\n");
        System.out.print("credit amount: " + credit.getCreditAmount() + "\n");

        assertEquals(creditResult, EnumCreditResult.REJECTED);
    }

    //Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL’nin altında ise
    //● kullanıcının kredi başvurusu onaylanır ve kullanıcıya 10.000 TL limit atanır.
    @Test
    void shouldReturnConfirmedCreditScore500Salary4999(){

        User user = CreateDummyUserBody();
        user.setSalary(4999);

        Credit credit = CreateUserCredit(user, 500L);
        EnumCreditResult creditResult = credit.getCreditResult();

        System.out.print("credit result: " + creditResult.toString() + "\n");
        System.out.print("credit amount: " + credit.getCreditAmount() + "\n");

        assertEquals(creditResult, EnumCreditResult.CONFIRMED);
    }

    /*
     * Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL’nin altında ise
*●     kullanıcının kredi başvurusu onaylanır ve kullanıcıya 10.000 TL limit atanır. (Kredi Sonucu:
     * Onay). Eğer teminat vermişse teminat bedelinin yüzde 10 u kadar tutar kredi limitine
     * eklenir.
     */
    @Test
    void shouldReturnConfirmedCreditScore500Salary4999AndGuaranteeFeePaid1000(){
        User user = CreateDummyUserBody();
        user.setSalary(4999);

        user.setGuaranteeAmount(1000);
        user.setEnumGuaranteeType(EnumGuaranteeType.GUARANTEED);

        Credit credit = CreateUserCredit(user, 999L);
        EnumCreditResult creditResult = credit.getCreditResult();

        System.out.print("credit result: " + creditResult.toString() + "\n");
        System.out.print("credit amount: " + credit.getCreditAmount() + "\n");

        assertEquals(credit.getCreditAmount(), (float)10100.0);
        assertEquals(creditResult, EnumCreditResult.CONFIRMED);
    }

    //Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL ile 10.000TL
    //arasında ise kullanıcının kredi başvurusu onaylanır ve kullanıcıya 20.000 TL limit atanır.
    @Test
    void shouldReturnConfirmedCreditScoreBetween500To1000Salary9999(){

        User user = CreateDummyUserBody();
        user.setSalary(9999);

        Credit credit = CreateUserCredit(user, 999L);
        EnumCreditResult creditResult = credit.getCreditResult();

        System.out.print("credit result: " + creditResult.toString() + "\n");
        System.out.print("credit amount: " + credit.getCreditAmount() + "\n");

        assertEquals(credit.getCreditAmount(), (float)20000.0);
        assertEquals(creditResult, EnumCreditResult.CONFIRMED);
    }

    //Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL ile 10.000TL
    //arasında ise kullanıcının kredi başvurusu onaylanır ve kullanıcıya 20.000 TL limit atanır.
    //(Kredi Sonucu:Onay) Eğer teminat vermişse teminat bedelinin yüzde 20 si kadar tutar
    //kredi limitine eklenir.
    @Test
    void shouldReturnConfirmedCreditScoreBetween500To1000Salary9999AndGuaranteeFeePaid1000(){
        User user = CreateDummyUserBody();
        user.setSalary(9999);

        user.setGuaranteeAmount(1000);
        user.setEnumGuaranteeType(EnumGuaranteeType.GUARANTEED);

        Credit credit = CreateUserCredit(user, 999L);
        EnumCreditResult creditResult = credit.getCreditResult();

        System.out.print("credit result: " + creditResult.toString() + "\n");
        System.out.print("credit amount: " + credit.getCreditAmount() + "\n");

        assertEquals(credit.getCreditAmount(), (float)20200.0);
        assertEquals(creditResult, EnumCreditResult.CONFIRMED);
    }

    //Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 10.000 TL’nin üstünde ise
    //kullanıcının kredi başvurusu onaylanır ve kullanıcıya AYLIK GELİR BİLGİSİ * KREDİ LİMİT
    //ÇARPANI/2 kadar limit atanır. (Kredi Sonucu:Onay)
    @Test
    void shouldReturnConfirmedCreditScoreBetween500To1000Salary15000(){

        User user = CreateDummyUserBody();
        user.setSalary(15000);

        Credit credit = CreateUserCredit(user, 700L);
        EnumCreditResult creditResult = credit.getCreditResult();

        System.out.print("credit result: " + creditResult.toString() + "\n");
        System.out.print("credit amount: " + credit.getCreditAmount() + "\n");

        assertEquals(credit.getCreditAmount(), (float)(user.getSalary() * (4/2)));
        assertEquals(creditResult, EnumCreditResult.CONFIRMED);
    }

    //Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 10.000 TL’nin üstünde ise
    //kullanıcının kredi başvurusu onaylanır ve kullanıcıya AYLIK GELİR BİLGİSİ * KREDİ LİMİT
    //ÇARPANI/2 kadar limit atanır. (Kredi Sonucu:Onay) Eğer teminat vermişse teminat
    //bedelinin yüzde 25 i kadar tutar kredi limitine eklenir.
    @Test
    void shouldReturnConfirmedCreditScoreBetween500To1000Salary15000AndGuaranteeFeePaid1000(){

        User user = CreateDummyUserBody();
        user.setSalary(15000);

        user.setGuaranteeAmount(1000);
        user.setEnumGuaranteeType(EnumGuaranteeType.GUARANTEED);

        Credit credit = CreateUserCredit(user, 700L);
        EnumCreditResult creditResult = credit.getCreditResult();

        System.out.print("credit result: " + creditResult.toString() + "\n");
        System.out.print("credit amount: " + credit.getCreditAmount() + "\n");

        assertEquals(credit.getCreditAmount(), (float)((user.getSalary() * (4/2))+ 250));
        assertEquals(creditResult, EnumCreditResult.CONFIRMED);
    }

    //Kredi skoru 1000 puana eşit veya üzerinde ise kullanıcıya AYLIK GELİR BİLGİSİ * KREDİ
    //LİMİT ÇARPANI kadar limit atanır.
    @Test
    void shouldReturnConfirmedCreditScoreGreaterThanOrEqualTo1000Salary15000(){

        User user = CreateDummyUserBody();
        user.setSalary(15000);

        Credit credit = CreateUserCredit(user, 1100L);
        EnumCreditResult creditResult = credit.getCreditResult();

        System.out.print("credit result: " + creditResult.toString() + "\n");
        System.out.print("credit amount: " + credit.getCreditAmount() + "\n");

        assertEquals(credit.getCreditAmount(), (float)(user.getSalary() * (4)));
        assertEquals(creditResult, EnumCreditResult.CONFIRMED);
    }
    
    //Kredi skoru 1000 puana eşit veya üzerinde ise kullanıcıya AYLIK GELİR BİLGİSİ * KREDİ
    //LİMİT ÇARPANI kadar limit atanır. (Kredi Sonucu: Onay) Eğer teminat vermişse teminat
    //bedelinin yüzde 50 si kadar tutar kredi limitine eklenir.
    @Test
    void shouldReturnConfirmedCreditScoreGreaterThanOrEqualTo1000Salary15000GuaranteeFee5000(){

        User user = CreateDummyUserBody();
        user.setSalary(15000);

        user.setGuaranteeAmount(5000);
        user.setEnumGuaranteeType(EnumGuaranteeType.GUARANTEED);

        Credit credit = CreateUserCredit(user, 1100L);
        EnumCreditResult creditResult = credit.getCreditResult();

        System.out.print("credit result: " + creditResult.toString() + "\n");
        System.out.print("credit amount: " + credit.getCreditAmount() + "\n");

        assertEquals(credit.getCreditAmount(), (float)((user.getSalary() * (4))+ 2500));
        assertEquals(creditResult, EnumCreditResult.CONFIRMED);
    }
    
}