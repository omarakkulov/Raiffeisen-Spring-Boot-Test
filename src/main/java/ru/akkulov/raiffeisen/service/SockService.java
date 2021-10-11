package ru.akkulov.raiffeisen.service;


import ru.akkulov.raiffeisen.model.Sock;

public interface SockService {

    Sock getSockByColorAndCottonPartAndOutcome(Sock comingSock);

    String getSockByOperation(String color, String operation, int cottonPart);

    Sock createSock(Sock sock);

    Sock getSocksById(long sockId);

    void deleteSocksById(long sockId);
}
