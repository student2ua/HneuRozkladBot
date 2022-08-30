### to read:

- https://tlgrm.ru/docs/bots
- [ReplyKeyboardMarkup](https://tlgrm.ru/docs/bots/api#inlinekeyboardbutton)
- [Создание телеграм-бота (Spring Boot, Kafka, PostgreSQL), часть первая](https://habr.com/ru/post/655329/)
- [github](https://github.com/RushianHaker/secretary-bot)
- [Telegram-бот на Java для самых маленьких — от старта до бесплатного размещения на heroku](https://habr.com/ru/post/528694/)
- [InlineKeyboard в Телеграмм ботах (Telegram Bots)](https://habr.com/ru/post/418905/)
- [TelegramBotBase](https://github.com/dp-ua/TelegramBotBase/tree/master)
- [See GitHub TelegramBots lib](https://github.com/rubenlagus/TelegramBots)
- [See GitHub TelegramBotsExample](https://github.com/rubenlagus/TelegramBotsExample)
- [Tutorial](https://github.com/MonsterDeveloper/java-telegram-bot-tutorial)
- [Во что обернулась пересылка MQTT-сообщений в Telegram?](https://habr.com/ru/post/683698/)

Есть потребность чтобы бот присылал сообщение не персонально, а в группу, чтобы другие участники могли видеть 
результаты? Организовываем! Создаем группу, добавляем туда нашего бота, добавляем туда бота 
RawDataBot, который нам сообщает id нашего группового чата.

start
- last select -> go ScheduleList
- select 
  - teacher
     - find by FIO -> go ScheduleList
     - select Department - Select FIO -> go ScheduleList
  - studentGroup
    - find by name -> go ScheduleList
    - select faculty - speciality - course - group - Select FIO -> go ScheduleList