package com.ecwo.bot.telegram.commands;

import com.ecwo.bot.telegram.repository.CommandTelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Properties;

@Slf4j
public class NonCommand {
    public String nonCommandExecute(Long chatId, String userName, String text) {
        log.debug(String.format("Пользователь %s. Начата обработка сообщения \"%s\", не являющегося командой",
                userName, text));
        Properties settings;
        String answer;
        try {
            //создаём настройки из сообщения пользователя
            settings = createSettings(text);
            //добавляем настройки в мапу, чтобы потом их использовать для этого пользователя при генерации файла
            saveUserSettings(chatId, settings);
            answer = "Настройки обновлены. Вы всегда можете их посмотреть с помощью /settings";
            //логируем событие, используя userName
        } catch (IllegalArgumentException e) {
            answer = e.getMessage() +
                    "\n\n Настройки не были изменены. Вы всегда можете их посмотреть с помощью /settings";
            //логируем событие, используя userName
        } catch (Exception e) {
            answer = "Простите, я не понимаю Вас. Возможно, Вам поможет /help";
            //логируем событие, используя userName
            log.error(userName + ", " + e.toString(), e);
        }
        log.debug(String.format("Пользователь %s. Завершена обработка сообщения \"%s\", не являющегося командой",
                userName, text));
        return answer;
    }

    @NotNull
    @Contract("null -> fail; !null -> new")
    private Properties createSettings(String s)  throws IllegalArgumentException{
        if (s==null) throw new IllegalArgumentException("Сообщение не является текстом");
        return new Properties();
    }
    /**
     * Добавление настроек пользователя в мапу, чтобы потом их использовать для этого пользователя при генерации файла
     * Если настройки совпадают с дефолтными, они не сохраняются, чтобы впустую не раздувать мапу
     * @param chatId id чата
     * @param settings настройки
     */
    private void saveUserSettings(Long chatId, Properties settings) {
        if (!settings.equals(CommandTelegramBot.getDefaultSettings())) {
            CommandTelegramBot.getUserSettings().put(chatId, settings);
        } else {
            CommandTelegramBot.getUserSettings().remove(chatId);
        }
    }
}
