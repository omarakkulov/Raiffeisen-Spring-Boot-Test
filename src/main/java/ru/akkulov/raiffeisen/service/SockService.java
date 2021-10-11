package ru.akkulov.raiffeisen.service;

import ru.akkulov.raiffeisen.exception.SockIncorrectDataException;
import ru.akkulov.raiffeisen.model.Sock;
import ru.akkulov.raiffeisen.util.Operation;

public interface SockService {

    /**
     * Метод ищет объект класса Sock из БД, который подходит по цвету (color)
     * и процентному содержанию хлопка в нем (cotton_part) и орудует с количеством носков (quantity)
     *
     * @param comingSock - объект класса Sock, приходящий в теле POST запроса 'api/socks/outcome'
     * @return объект типа Sock с измененным состоянием
     * @throws SockIncorrectDataException
     */
    Sock getSockByColorAndCottonPartAndOutcome(Sock comingSock);

    /**
     * Метод ищет общее количество всех носков, подходящих по параметрам в запросе
     *
     * @param color      - цвет носков
     * @param operation  - 'moreThan', 'lessThan', 'equal'
     * @param cottonPart - процентное содержание хлопка в носках
     * @return строковое представление общего количества носков, удовлетворяющих параметрам
     */
    String getSockByOperation(String color, Operation operation, int cottonPart);

    /**
     * @throws SockIncorrectDataException
     */
    Sock createSock(Sock sock);

    Sock getSocksById(long sockId);

    void deleteSocksById(long sockId);
}
