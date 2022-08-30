package com.ecwo.bot.telegram.repository;

import com.ecwo.bot.telegram.model.Teacher;
import com.ecwo.bot.telegram.service.TeacherService;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Getter
//@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final String botUsername;
    private final String botToken;
    private final TeacherService teacherService;

    private Message requestMessage = new Message();
    private final SendMessage response = new SendMessage();

    public TelegramBot(
            @NotNull TelegramBotsApi api,
            @Value("${telegram-bot.name}") String botUsername,
            @Value("${telegram-bot.token}") String botToken,
            TeacherService teacherService
    ) throws TelegramApiException {

        this.botUsername = botUsername;
        this.botToken = botToken;
        this.teacherService = teacherService;
        api.registerBot(this);

    }


    /**
     * Этот метод вызывается при получении обновлений через метод GetUpdates.
     *
     * @param request Получено обновление
     */
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update request) {
        requestMessage = request.getMessage();
        response.setChatId(requestMessage.getChatId().toString());

        var entity = new Teacher(
                0, requestMessage.getChat().getUserName()
                );

        if (request.hasMessage() && requestMessage.hasText())
            log.info("Working onUpdateReceived, request text[{}]", request.getMessage().getText());

        if (requestMessage.getText().equals("/start"))
            defaultMsg(response, "Напишите команду для показа списка: \n " + "/teacher - показать преподов");
        else if (requestMessage.getText().equals("/teacher"))
            getTeacher(response);
        else
            defaultMsg(response, "unknown command) \n ");

        log.info("Working, text[{}]", requestMessage.getText());

      /*  if (requestMessage.getText().startsWith("/")) {
            entity.setStartWord("команда: ");
            producerService.sendMessage( entity);
        } else {
            entity.setStartWord("мысль: ");
            producerService.sendMessage( entity);
            userService.insert(entity);
        }*/
    }

    private void getTeacher(SendMessage response) throws TelegramApiException {
        List<Teacher> teachers = teacherService.getTeachers();
        if (teachers.isEmpty()) {
            defaultMsg(response, "В списке нет teachers. \n");
        } else {
            defaultMsg(response, "Вот список ваших teachers: \n");
            for (Teacher txt : teachers) {
                response.setText(txt.toString());
                execute(response);
            }
        }

    }
    private void defaultMsg(SendMessage response, String msg) throws TelegramApiException {
        response.setText(msg);
        execute(response);
    }
}
