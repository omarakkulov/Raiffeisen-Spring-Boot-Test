package ru.akkulov.raiffeisen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akkulov.raiffeisen.model.Sock;
import ru.akkulov.raiffeisen.service.SockService;
import ru.akkulov.raiffeisen.util.Operation;

@RestController
@RequiredArgsConstructor
@RequestMapping("/socks")
public class SockController {
    private final SockService sockService;

    @PostMapping("/income")
    public ResponseEntity<Sock> addSock(@RequestBody Sock sock) {
        return ResponseEntity.ok(sockService.createSock(sock));
    }

    @PostMapping("/outcome")
    public ResponseEntity<Sock> outComeSocks(@RequestBody Sock comingSock) {
        return ResponseEntity.ok(sockService.getSockByColorAndCottonPartAndOutcome(comingSock));
    }

    @GetMapping
    public ResponseEntity<String> getQuantityByParameters(@RequestParam String color,
                                                          @RequestParam Operation operation,
                                                          @RequestParam int cottonPart) {

        return ResponseEntity.ok(sockService.getQuantityByParameters(color, operation, cottonPart));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sock> getSocksById(@PathVariable long id) {
        return ResponseEntity.ok(sockService.getSocksById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sock> deleteSocksById(@PathVariable long id) {
        sockService.deleteSocksById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
