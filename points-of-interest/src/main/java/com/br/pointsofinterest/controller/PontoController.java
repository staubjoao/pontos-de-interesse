package com.br.pointsofinterest.controller;

import com.br.pointsofinterest.dto.PontoDTO;
import com.br.pointsofinterest.model.Ponto;
import com.br.pointsofinterest.service.impl.PontoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import response.Response;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ponto")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PontoController {

    @Autowired
    private PontoServiceImpl service;

    @GetMapping
    public List<Ponto> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Response<Ponto>> save(@Valid @RequestBody PontoDTO pontoDTO, BindingResult result) {
        Ponto ponto = new Ponto();
        ponto.setX(pontoDTO.getX());
        ponto.setY(pontoDTO.getY());
        ponto.setDescricao(pontoDTO.getDescricao());
        return service.save(ponto, result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Ponto>> getById(@PathVariable String id) {
        return service.getById(UUID.fromString(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Ponto>> deleteById(@PathVariable String id) {
        return service.deleteById(UUID.fromString(id));
    }

    @PutMapping
    public ResponseEntity<Response<Ponto>> put(@Valid @RequestBody Ponto ponto, BindingResult result) {
        return service.save(ponto, result);
    }

}
