package ru.akkulov.raiffeisen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.akkulov.raiffeisen.model.Sock;
import ru.akkulov.raiffeisen.service.SockService;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/socks")
public class SockController {
    private final SockService sockService;

    @PostMapping("/income")
    public ResponseEntity<Sock> addSock(@RequestBody Sock sock) {
        var currentSock = sockService.createSock(sock);
        return ResponseEntity.ok(currentSock);
    }

    @PostMapping("/outcome")
    public ResponseEntity<Sock> outComeSocks(@RequestBody Sock comingSock) {
        var currentSock = sockService.getSockByColorAndCottonPartAndOutcome(comingSock);
        return ResponseEntity.ok(currentSock);
    }

    @GetMapping
    public ResponseEntity<String> getSocksByParameters(@RequestParam String color,
                                                       @RequestParam String operation,
                                                       @RequestParam int cottonPart) {

        var sock = sockService.getSockByOperation(color, operation, cottonPart);

        return ResponseEntity.ok(sock);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sock> getSocksById(@PathVariable long id) {
        var currentSock = sockService.getSocksById(id);
        return ResponseEntity.ok(currentSock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sock> deleteSocksById(@PathVariable long id) {
        sockService.deleteSocksById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
