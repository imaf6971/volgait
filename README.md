# Аналитика приложений.

## Описание.

Данное приложение было написано как решение задания отборочного этапа Международной цифровой олимпиады «Волга-IT`22» по дисциплине «Прикладное программирование (Java/C#)».

Приложение реализовано с использованием Java + Spring 

Программа представляет собой web приложение для сохранения аналитики.

## Инструкция по развертыванию окружения.

```sh
./mvnw install -DskipTests

docker-compose up
```

## Инструкция по использованию.

После запуска приложения локально, оно доступно по адресу.

localhost:8080/

Для использования приложения необходима регистрация.

После того, как вы зарегистрировались и создали приложение, ему будет присвоен уникальный идентификатор, по которому можно будет отправлять запросы.

Запросы отправляются на endpoint /request с помощью метода POST и принимает следующие параметры:

1. uuid - Уникальный идентификатор приложения.
2. title - Название для запроса.
3. additionalInformation - дополнительная информация по запросу.

Все три параметра являются обязательными.

Пример запроса:

POST /request?uuid=ec59af8a&title=registration&additionalInformation=username:imaf6971

## Использованные технологии.

1. Язык программирования Java 17
2. Spring-фреймворк:
    - Spring Boot. Компонент для автоматической конфигурации Spring проекта. Позволяет создавать инфраструктурные обьекты с минимальным количеством конфигурации.
    - Spring Data JPA. Компонент для работы с СУБД. Включает в себя ORM Hibernate. Позволяет писать уровень Repository без написания SQL запросов
    - Spring MVC. Компонент для реализации Web слоя. Использует паттерн MVC как основу.
    - Thymeleaf. Библиотека-шаблонизатор для HTML. Используется для реализации View слоя.
    - Spring Security. Компонент, реализующий Аутентификацию и Авторизацию.
3. СУБД PostgreSQL.
4. Docker (+ compose) для развёртывания окружения  
