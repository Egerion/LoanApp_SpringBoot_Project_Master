package com.loanapp.egedemirbas.Credit.Entity;

import com.loanapp.egedemirbas.Credit.Enum.EnumCreditResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="MPM_USER_CREDIT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private Date insDate;
    private float creditAmount;
    private Long userIdentityNumber;
    private Date userDateOfBirth;
    private EnumCreditResult creditResult;
}
