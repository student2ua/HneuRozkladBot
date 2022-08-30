package com.ecwo.bot.telegram.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
/**
 * User: tor
 * Date: 008, 08.04.2022
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class AppConfig {
    @Bean
    ObjectMapper customObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    TelegramBotsApi telegramBotsApi() throws TelegramApiException{
        return new TelegramBotsApi(DefaultBotSession.class);
    }
    @Bean
    public WebClient openApiClient() {
        return WebClient.create("https://mark.hneu.edu.ua/");
    }
}
