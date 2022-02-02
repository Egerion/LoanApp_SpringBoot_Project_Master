<div align="center">
    <div style="height:120px;">
        <h1 style="display:inline;color:red;">Loan App</h1>
        <h6>by Ege Demirbaş</h6>
    </div>
    <p align="center">
        <img src="https://www.aalpha.net/wp-content/uploads/2021/07/reactjs.gif" width="15%"/>
        <img src="https://upload.wikimedia.org/wikipedia/tr/2/2e/Java_Logo.svg" width="4%"/>
        <img src="./resources/images/hibernate.svg" width="5%"/>
        <img src="https://upload.wikimedia.org/wikipedia/commons/1/10/CSS3_and_HTML5_logos_and_wordmarks.svg" width="10%"/> 
        <img src="https://upload.wikimedia.org/wikipedia/commons/b/ba/Javascript_badge.svg" width="6%"/>
        <a>
            <a style="margin-right:0px;margin-left:60px;color:green;">Powered by</a>
            <img src="https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg" width="20%"/>
        </a>
    </p>
</div>

---

## About

A Restfull Application based on Spring Framework which consist of two sub projects. 
First project is back-end of the Loan App and the second one is the front-end part.
Application capabilities are:

    - Registering to system as new user to apply for credit.
    - Credit status inquiry.
    - List of users registered into system.
    - Informing users about their credit application result by sending sms to their registered phone numbers.

Project's goal is available in [Project PDF](./2022_01_18_n11_TalentHub_Java_Spring_Bootcamp_Bitirme_Projesi.pdf)

<div>
    <p align="center">
        <img src="./resources/images/credit-registry-page-01.png"  width="45%" text="Registration Page"/>
        <img src="./resources/images/quer-page-01.png" width="47%" text="Inquiry Page"/>
    </p>
</div>

## Technical Information

Project consists of two sections; First section is back-end part of the project, which based on Spring Framework. Postgres SQL is getting used as database and Hibernate Framework handles the interaction.

Backend part of the project is ready to deploy and currently configured to running at  <span style="color:red">localhost:8081</span>.
[Open Api Documentation](./openapi.json) is available in main folder.

---

### Back End

Here are the list of services and entities exist inside of the back-end part.

    Entities:
            User
            Credit
    Controllers:
            UserController
            CreditController
    Dao's(Data Access Objec):
            UserDao
            CreditDao
    Dto's(Data Transfer Object):
            UserDto
    Converters:
            UserConverter - converts userDto to user.
    Exceptions:
            UserNotFoundException
            CreditNotFoundException
    Enums:
            Enum Credit Guarantee Type: determines whether user paid gurantee fee while applying for credit or not.
                - Paid
                - Not Paid
            Enum Credit Result: determines the result of credit inquiry of a user.
                - CREDIT_CONFIRMED
                - CREDIT_REJECTED
    Services:
            UserEntityService
            CreditEntityService
            CreditModelService: Determines the credit amount and result by getting necessary information from user.
            SmsMessageService: Pushes sms to user and informs It about the result of credit transaction.
    General:
            TestLog: logging progress of systems.


#### Calculating The Credit Score

Credit score calculation is based on simple algorithm that takes the age, salary and guranatee index from user and combines with pre-determined coefficients to obtain user credit score.

Method Name: <span style="color:red">CalculateUserCredit</span>
```
credit score = ∑〖1.354 x ageIndex+ 0.85 x salaryIndex+2.1.2 x guaranteeIndex〗
```

once the credit score is calculated, then <span style="color:red">CreateUserCredit</span> method is getting called and applies the rules that determined by our project and returns the result of user credit.

#### Sending Text Message To User

The [Twilio Api](./resources/links/Twilio.url) is getting used to inform user about their credit application result. Once applied, user will recieve text message which contains information about the result of credit application along with It's amount...

<div>
    <p align="center">
        <img src="./resources/images/message.jpg"  width="30%" text="User List Page"/>
    </p>
</div>

Maven, pom.xml configuration:

```xml
<dependency>
  <groupId>com.twilio.sdk</groupId>
  <artifactId>twilio</artifactId>
  <version>8.25.1</version>
</dependency>
```

---

## Front End

<div>
    <p align="center">
        <img src="./resources/images/user-list-page-01.png"  width="70%" text="User List Page"/>
    </p>
</div>

Frontend is a React project, which serves as user interface. Here, new users can register to system and apply for credit. Pre-applied users can always inquiry their credit apply result by entering their user-identity number and date of births.
Note that users can only apply once for credit.

Frontend part of the project is ready to deploy and currently configured to running at  <span style="color:red">localhost:3030</span>.
To deploy front-end project, you need to use fallowing command in the root project path.

```node
npm start
```

## Unit Test

There are nine unit tests that convers all the potential cases of user credit application results to validate the functionality of credit application algorithm.

List of Unit Tests:

    1- shouldReturnRejectedCreditScore499 - validating the result of credit application.
    2- shouldReturnConfirmedCreditScore500Salary4999 - validating the result of credit application.
    3- shouldReturnConfirmedCreditScore500Salary4999AndGuaranteeFeePaid1000 - validating the cross conditions and returning result of an application.
    4- shouldReturnConfirmedCreditScoreBetween500To1000Salary9999
    5- shouldReturnConfirmedCreditScoreBetween500To1000Salary9999AndGuaranteeFeePaid1000
    6- shouldReturnConfirmedCreditScoreBetween500To1000Salary15000
    7- shouldReturnConfirmedCreditScoreBetween500To1000Salary15000AndGuaranteeFeePaid1000
    8- shouldReturnConfirmedCreditScoreGreaterThanOrEqualTo1000Salary15000
    9- shouldReturnConfirmedCreditScoreGreaterThanOrEqualTo1000Salary15000GuaranteeFee5000
---

## License

Loan App is an application that created under the [MIT](https://opensource.org/licenses/gpl-3.0.html)
license, see the [license](./LICENSE) file in the project root for the full license text.