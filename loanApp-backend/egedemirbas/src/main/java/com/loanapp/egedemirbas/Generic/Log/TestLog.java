package com.loanapp.egedemirbas.Generic.Log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestLog {

    public static void print(String message){
        System.out.print("Test Log Called");
        log.info("Info:" + message + " ");
    }
}
