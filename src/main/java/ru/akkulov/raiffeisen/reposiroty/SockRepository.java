package ru.akkulov.raiffeisen.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akkulov.raiffeisen.model.Sock;

import java.util.List;

@Repository
public interface SockRepository extends JpaRepository<Sock, Long> {

    List<Sock> findAllByColor(String color);

    Sock findSockByColorAndCottonPart(String color, int cottonPart);
}
