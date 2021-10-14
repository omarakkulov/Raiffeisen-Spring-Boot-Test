# Raiffeisen-Spring-Boot-Test

Тестовое задание - https://github.com/Raiffeisen-DGTL/cib-interns-test-task

## Используемый стек:
Java 11, PostgreSQL, Liquibase, SpringBoot, Spring Data Jpa, Lombok, Maven

## Подробнее:
### Для запуска приложения требуется иметь PostgreSQL на устройстве.
###### 1. Создать базу данных под названием "raiffeisen_db"
###### 2. В папке application.properties на месте этих строк вставить свои значений логина и пароля от БД:
  ###### spring.datasource.username=${Свое значение} 
  ###### spring.datasource.password=${Свое значение} 
###### 3. Приложение находится на порте 8081, измените при надобности:
   ###### server.port=${Свое значение}
   
#### К сожалению, не успел разобраться с докером и развернуть БД в докер-контейнере :(
