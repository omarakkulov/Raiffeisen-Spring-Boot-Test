package ru.akkulov.raiffeisen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SockGlobalExceptionHandler {

    /*
     * метод отрабатывает на SockIncorrectDataException, когда,
     * например, нет носков по указанному id, или же когда в post методе
     * введены невалидные данные для параметров cottonPart и quantity,
     * надеюсь это правильное решение
     */
    @ExceptionHandler
    public ResponseEntity<SockIncorrectDataObject> handleException(
            SockIncorrectDataException exception) {

        SockIncorrectDataObject data = new SockIncorrectDataObject();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<SockIncorrectDataObject> handleException(
            Exception exception) {

        SockIncorrectDataObject data = new SockIncorrectDataObject();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
