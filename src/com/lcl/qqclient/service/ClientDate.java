package com.lcl.qqclient.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientDate {
    public String getSendDate(){
        LocalDateTime today = LocalDateTime.now();
        System.out.println(today);
        LocalDateTime dateTime = LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), today.getHour(), today.getMinute(),today.getSecond());
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return formattedDateTime;
    }
}
