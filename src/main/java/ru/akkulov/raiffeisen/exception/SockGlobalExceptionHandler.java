package ru.akkulov.raiffeisen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SockGlobalExceptionHandler {

    /**
     * Метод срабатывает, когда выбрасывается SockIncorrectDataException,
     * в том случае, например, когда нет носков по указанному id, или же когда в post методе
     * введены невалидные данные для параметров cottonPart и quantity,
     * надеюсь это правильное решение
     *
     * @return новый JSON ответ в виде "info":"Сообщение об ошибке"
     */
    @ExceptionHandler
    public ResponseEntity<SockIncorrectDataObject> handleException(
            SockIncorrectDataException sockIncorrectDataException) {

        SockIncorrectDataObject data = new SockIncorrectDataObject();
        data.setInfo(sockIncorrectDataException.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    /**
     * Метод срабатывает при любых эксепшенах, возникающих при работе приложения
     *
     * @return новый JSON ответ в виде "info":"Сообщение об ошибке"
     */
    @ExceptionHandler
    public ResponseEntity<SockIncorrectDataObject> handleException(Exception exception) {

        SockIncorrectDataObject data = new SockIncorrectDataObject();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
