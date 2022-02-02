package com.loanapp.egedemirbas.User.Dto;

import com.loanapp.egedemirbas.User.Enum.EnumGuaranteeType;

import java.util.Date;

public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private Long identityNumber;
    private String phoneNumber;
    private float salary;
    private Long age;
    private Date dateOfBirth;
    private float guaranteeAmount;
    private EnumGuaranteeType enumGuaranteeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(Long identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public float getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(float guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public EnumGuaranteeType getEnumGuaranteeType() {
        return enumGuaranteeType;
    }

    public void setEnumGuaranteeType(EnumGuaranteeType enumGuaranteeType) {
        this.enumGuaranteeType = enumGuaranteeType;
    }
}
