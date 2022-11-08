package com.reactive.servicereactive.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateUtil {

    private DateUtil(){
        throw new IllegalArgumentException();
    }

    public static LocalDate convertData(String inputDate){
        try{
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(inputDate,formatDate);
        }catch(DateTimeParseException ex){
            log.error("error because date time format -> {} ",ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
