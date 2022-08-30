package com.ecwo.bot.telegram;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
/**
 * User: tor
 * Date: 008, 08.04.2022
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@SpringBootApplication
public class RozkladBotApp {
    public static void main(String[] args) {
        SpringApplication.run(RozkladBotApp.class, args);
    }
}
