package com.loanapp.egedemirbas.Credit.Service;

import com.loanapp.egedemirbas.Generic.Log.TestLog;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

//https://www.twilio.com/docs/libraries/java
public class SmsMessageService {

    private String number;
    private String message;

    // Find your Account Sid and Token at twilio.com/user/account
    private static final String ACCOUNT_SID = ""; //account id
    private static final String AUTH_TOKEN = ""; //account token
    private static final String AUTH_NUMBER = "+"; //account number


    SmsMessageService(String number, String message){
        this.number = number;
        this.message = message;
    }

    public void sendMessage(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = null;
        try{
            message = Message.creator(new PhoneNumber(this.number), new PhoneNumber(AUTH_NUMBER), this.message).create();
        }catch (Error e){

        }
        TestLog.print("message sent to user: " + message.getSid());
    }
}
