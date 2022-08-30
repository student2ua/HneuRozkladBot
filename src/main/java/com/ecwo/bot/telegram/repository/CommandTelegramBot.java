package com.ecwo.bot.telegram.repository;

import com.ecwo.bot.telegram.commands.NonCommand;
import com.ecwo.bot.telegram.commands.SettingsCommand;
import com.ecwo.bot.telegram.commands.StartCommand;
import com.ecwo.bot.telegram.config.Util;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Getter
@Component
public class CommandTelegramBot extends TelegramLongPollingCommandBot {
    private final String botUsername;
    private final String botToken;
    /**
     * Настройки файла для разных пользователей. Ключ - уникальный id чата
     */
    @Getter
    private static Map<Long, Properties> userSettings;

    //Настройки по умолчанию
    @Getter
    private static final Properties defaultSettings = new Properties();

    //Класс для обработки сообщений, не являющихся командой
    private final NonCommand nonCommand;

    public CommandTelegramBot(
            @NotNull TelegramBotsApi api,
            @Value("${telegram-bot.name}") String botUsername,
            @Value("${telegram-bot.token}") String botToken) throws TelegramApiException {
        this.botUsername = botUsername;
        this.botToken = botToken;
        api.registerBot(this);
        //создаём вспомогательный класс для работы с сообщениями, не являющимися командами
        this.nonCommand = new NonCommand();
        //регистрируем команды
        register(new StartCommand("start", "Старт"));
        register(new SettingsCommand("settings", "Мои настройки"));
        register(new HelpCommand("help", "Помощь", "extendedDescription"));
        userSettings = new HashMap<>();
    }

    @Override
    public void processNonCommandUpdate(@NotNull Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String userName = Util.getUserName(message);
        String answer = nonCommand.nonCommandExecute(chatId, userName, message.getText());
        setAnswer(chatId, userName, answer);
    }

    private void setAnswer(@NotNull Long chatId, String userName, String text) {
        SendMessage message = new SendMessage(chatId.toString(), text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(userName + "," + e.getMessage(), e);
//           e.printStackTrace();
        }
    }

    /**
     * Получение настроек по id чата. Если ранее для этого чата в ходе сеанса работы бота настройки не были установлены, используются настройки по умолчанию
     */
    public static Properties getUserSettings(Long chatId) {
        Map<Long, Properties> userSettings = CommandTelegramBot.getUserSettings();
        Properties settings = userSettings.get(chatId);
        if (settings == null) {
            return defaultSettings;
        }
        return settings;
    }

}
