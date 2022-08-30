package com.ecwo.bot.telegram.service;

import com.ecwo.bot.telegram.model.Teacher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Map;
/**
 * https://www.techgeeknext.com/spring-boot/webflux/spring-boot-webclient-example
 * */
@Service
@Slf4j
//@RequiredArgsConstructor
public class ECWOService {
    private final WebClient client;
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    @Autowired
    public ECWOService(WebClient client) {
        this.client = client;
    }

//    https://mark.hneu.edu.ua/openAPI/rest/faculties/
    public Map<Integer, String> getFaculties() {
        return client.get()
                .uri("openAPI/rest/faculties/")
                .retrieve()
//                .bodyToFlux(Map.class)
//                .flatMap(map->map.get("id"),map.get("facultyName") )
                //[{"id":98,"shortName":"Підг Іноз","facultyName":"Підготовче відділення для іноземців"}
                .bodyToMono(new ParameterizedTypeReference<Map<Integer, String>>() {})
                .block(REQUEST_TIMEOUT);
    }
    public String getFaculty(long id) {
        return client.get()
                .uri("/openAPI/rest/faculties/" + id)
                .retrieve()
                .bodyToMono(String.class)
                .block(REQUEST_TIMEOUT);
    }

    public String getListSpeciality(long facultyId) {
        return client.get()
                .uri("/openAPI/rest/faculties/" + facultyId + "/specialities")
                .retrieve()
                .bodyToMono(String.class)
                .block(REQUEST_TIMEOUT);
    }
//"id":881,"shortName":"","specialityName":"Кафедри ЕІ","specialityCode":"101                 ","qualName":"","specialityNumber":null}
    public String getSpeciality(long facultyId, long id) {
        return client.get()
                .uri("/openAPI/rest/faculties/" + facultyId + "/specialities/" + id)
                .retrieve()
                .bodyToMono(String.class)
                .block(REQUEST_TIMEOUT);
    }
}
