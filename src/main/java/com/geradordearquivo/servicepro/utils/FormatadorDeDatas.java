package com.geradordearquivo.servicepro.utils;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FormatadorDeDatas {

    public static LocalDateTime FormatadorDeDatas(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDateTime localDateTime = LocalDateTime.parse(data, formatter);

        return localDateTime;
    }
}
