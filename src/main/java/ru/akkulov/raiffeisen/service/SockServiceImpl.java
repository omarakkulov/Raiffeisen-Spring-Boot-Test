package ru.akkulov.raiffeisen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akkulov.raiffeisen.exception.SockIncorrectDataException;
import ru.akkulov.raiffeisen.model.Sock;
import ru.akkulov.raiffeisen.reposiroty.SockRepository;
import ru.akkulov.raiffeisen.util.Operation;

@Service
@RequiredArgsConstructor
@Transactional
public class SockServiceImpl implements SockService {
    private final SockRepository sockRepository;

    @Override
    public Sock createSock(Sock sock) {
        if (sock.getCottonPart() >= 0 && sock.getCottonPart() <= 100) {
            if (sock.getQuantity() > 0) {
                return sockRepository.save(sock);
            }
        }
        throw new SockIncorrectDataException("cottonPart value must be between 0-100, " +
                "quantity value must be > 0");
    }

    /**
     * Метод ищет носки, подходящие по цвету и процентному содержанию хлопка в них
     *
     * @param comingSock - объект класса Sock, приходящий в теле POST запроса 'api/socks/outcome'
     * @return объект типа Sock с измененным состоянием
     */
    @Override
    public Sock getSockByColorAndCottonPartAndOutcome(Sock comingSock) {

        String comingSockColor = comingSock.getColor();
        int comingSockCottonPart = comingSock.getCottonPart();
        int comingSockQuantity = comingSock.getQuantity();

        var currentSock = sockRepository.findSockByColorAndCottonPart(
                comingSockColor, comingSockCottonPart);

        if (currentSock == null) {
            throw new SockIncorrectDataException(
                    "We apologize for the inconvenience, " +
                            "socks with this cottonPart value is not available in our warehouse right now");
        }

        if (currentSock.getQuantity() >= comingSockQuantity) {

            int newQuantity = currentSock.getQuantity() - comingSockQuantity;
            currentSock.setQuantity(newQuantity);

        } else {

            int quantityOfCurrentSock = currentSock.getQuantity();
            throw new SockIncorrectDataException
                    (String.format("We apologize for the inconvenience, there are only %s pairs of socks in " +
                                    "store warehouse right now, please select fewer pairs than you need",
                            quantityOfCurrentSock));
        }

        return sockRepository.save(currentSock);
    }

    /**
     * Метод ищет общее количество всех носков, подходящих по параметрам в запросе
     *
     * @param color      - цвет носков
     * @param operation  - 'moreThan, lessThan, equal'
     * @param cottonPart - процентное содержание хлопка в носках
     * @return строковое представление общего количества носков, удовлетворяющих параметрам
     */
    @Override
    public String getSockByOperation(String color, String operation, int cottonPart) {

        var socks = sockRepository.findAllByColor(color);

        /*
         * Если параметр operation = "moreThan"
         */
        if (operation.equals(Operation.moreThan.toString())) {

            return socks.stream()
                    .filter(sock -> sock.getCottonPart() > cottonPart)
                    .map(Sock::getQuantity)
                    .reduce(0, Integer::sum)
                    .toString();
        }

        /*
         * Если параметр operation = "lessThan"
         */
        if (operation.equals(Operation.lessThan.toString())) {

            return socks.stream()
                    .filter(sock -> sock.getCottonPart() < cottonPart)
                    .map(Sock::getQuantity)
                    .reduce(0, Integer::sum)
                    .toString();
        }

        /*
         * Если параметр operation = "equal"
         */
        if (operation.equals(Operation.equal.toString())) {

            return socks.stream()
                    .filter(sock -> sock.getCottonPart() == cottonPart)
                    .map(Sock::getQuantity)
                    .reduce(0, Integer::sum)
                    .toString();
        }

        return "No socks with this parameters";
    }

    @Override
    public Sock getSocksById(long sockId) {
        return sockRepository.findById(sockId)
                .orElseThrow(() -> new SockIncorrectDataException(String.format(
                        "Sock with id: %s is not found!", sockId)));
    }

    @Override
    public void deleteSocksById(long sockId) {
        sockRepository.deleteById(sockId);
    }
}
